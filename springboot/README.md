Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Implementation](#Implementation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
- The idea behind this project is to replace a legacy trading system, a monolithic application that simply does not scale well, and is hard to manage. This new trading system aims to utilize the microservice architecture and Springboot framework to alleviate these issues.
- This is a PoC (proof of concept) so no actual banking information is used, and the scope is much smaller. Namely, this app can create and manage clients/accounts, and execute security orders on stocks defined by IEX Cloud’s REST API.
- All code was written in Java using standard libraries like JdbcTemplate, Springboot framework managed dependencies, Swagger created the UI, PostgreSQL was used as the backend database for accounts/traders/security orders/etc., Maven was used to build the project, and finally Docker was used to deploy the project.

# Quick Start
- Prerequisites: Docker, CentOS 7
- Docker:
  - Pull images:
	```bash
	#Docker version must be 17.05 or higher 
docker -v 

#download images 
docker pull vishaysingh35/trading-app:latest 
docker pull vishaysingh35/trading-psql:latest 
	```
  - Create a docker network:
	```bash
	docker network create --driver bridge trading-net
	
	#Verify
docker network ls
```
  - Start containers:
```bash
docker run --name trading-psql-dev \ 
-e POSTGRES_PASSWORD=password \
-e POSTGRES_USER=postgres \ 
--network trading-net \ 
-d -p 5432:5432 trading-psql

#verify
docker ps

#set IEX credential 
IEX_PUB_TOKEN="your_token" 

docker run --name trading-app-dev \ 
-e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/postgres" \ 
-e "PSQL_USER=postgres" \ 
-e "PSQL_PASSWORD=password" \ 
-e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \ 
--network trading-net \ 
-p 8080:8080 -t trading-app

#There should be two running containers
docker container ls

#Visit the webpage (See Swagger Section)
```
  - How to build images from scratch (skip if you did the steps above):
```bash
cd ./springboot/psql 
docker build -t trading-psl . 
docker image ls -f reference=trading-psql

cd ./springboot/ 
docker build -t trading-app . 
docker image ls -f reference=trading-psql

#Then follow the same steps from as above (verify using psql cli that all relations were initialized)
```
- Try the trading-app with SwaggerUI:
  - Visit the webpage on your browser: `http://localhost:8080/swagger-ui.html`

# Implemenation
## Architecture
- Draw a component diagram that contains controllers, services, DAOs, SQL, IEX Cloud, WebServlet/Tomcat, HTTP client, and SpringBoot. (you must create your own diagram)
- briefly explain the following components and services (3-5 sentences for each)
  - Controller layer (e.g. handles user requests....)
  - Service layer
  - DAO layer
  - SpringBoot: webservlet/TomCat and IoC
  - PSQL and IEX

## REST API Usage
### Swagger
What's swagger (1-2 sentences, you can copy from swagger docs). Why are we using it or who will benefit from it?
### Quote Controller
- High-level description for this controller. Where is market data coming from (IEX) and how did you cache the quote data (PSQL). Briefly talk about data from within your app
- briefly explain each endpoint
  e.g.
  - GET `/quote/dailyList`: list all securities that are available to trading in this trading system blah..blah..
### Trader Controller
- High-level description for trader controller (e.g. it can manage trader and account information. it can deposit and withdraw fund from a given account)
- briefly explain each endpoint
### Order Controller
- High-level description for this controller.
- briefly explain each endpoint
### App controller
- briefly explain each endpoint
### Optional(Dashboard controller)
- High-level description for this controller.
- briefly explain each endpoint

# Test 
How did you test your application? Did you use any testing libraries? What's the code coverage?

# Deployment
- docker diagram including images, containers, network, and docker hub
e.g. https://www.notion.so/jarviscanada/Dockerize-Trading-App-fc8c8f4167ad46089099fd0d31e3855d#6f8912f9438e4e61b91fe57f8ef896e0
- describe each image in details (e.g. how psql initialize tables)

# Improvements
If you have more time, what would you improve?
- at least 3 improvements

