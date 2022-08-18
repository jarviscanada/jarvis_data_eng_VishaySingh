# Cloud & DevOps Project
# Introduction
- The application architecture was built in an Azure resource group, with Kubernetes. The scale set involves a load balancer that will automatically scale the number of application server pods based on the current load. This configuration is set to max out at only 2 server pods, due to Azure’s free tier limitations. The pod(s) communicates with the psql pod to access the psql database at port 5432, which persists the data for the application server.
- Two deployment environments were made for both the dev and prod pipelines. Essentially, the two environments are used for development and production respectfully, where dev receives frequent increments, and prod only receives the completed sprint increments (they would push to develop and master branches respectfully, but for this project branch pushes were restricted).
- The overall structure of both pipelines (dev and prod) are the same. Code is pulled from this repo, built using `springboot/Dockerfile` (note that testing was excluded since the `springboot` app is not in active development), and then pushed as an image to the container registry hosted by ACR. This completes the continuous integration, but as for continuous deployment, both dev and prod will push to their respective branches. 

# Application Architecture

![devops_arch](https://user-images.githubusercontent.com/56552567/185453546-a17cf0dc-200d-4186-bd52-94ad10f4b04f.png)

![k8s_deploy](https://user-images.githubusercontent.com/56552567/185459566-1bc2ec68-c642-49a3-8ce9-f78745aa1f71.png)


# Jenkins CI/CD pipeline
- `Build`: Code is pulled from this repo, built using `springboot/Dockerfile`, and then pushed as an image to the container registry hosted by ACR. 
- `Test`: testing was excluded since the `springboot` app is not in active development.
- `Deploy`: Both dev and prod push to their respective branches.

![pipeline](https://user-images.githubusercontent.com/56552567/185477045-fdb38ff7-570f-49fe-816e-6ac7f2890a5a.png)


# Improvements
1. Add testing to pipelines: for future development with the `springboot` project, adding testing to each pipeline is necessary.
2. Add security checking to pipelines: for serious production releases, automated security auditing at minimum is necessary.
3. Add code quality checking: code quality has already been checked, but for future development to the `springboot` project, adding code quality checking to the pipelines is necessary to ensure good code styling.


