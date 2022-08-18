# Trading Frontend Application
# Introduction
The goal of this project is to build upon the SpringBoot project’s backend trading app by implementing an MVP (Minimum Viable Product) frontend built with pure React.js. The idea is to have a UI that traders can use to add accounts, check stock quotes, and read/update their account info (their balance for example). The design was simplistic; a dashboard page which displays all traders in the backend database, and a navigation bar that links to the stock quotes page. All components were built using React.js, styling was written using S/CSS, packages were managed using NPM, testing was primarily done in the browser, and finally, the app was deployed using Docker. 

# Quick Start
- Prerequisite: Docker

- [How to install Node and NPM](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

- Docker Setup (Backend)

  ```sh
  #Pull the images
  docker pull vishaysingh35/trading-app:latest
  docker pull vishaysingh35/trading-psql:latest
  
  #Create a docker network
  docker network create --driver bridge trading-net
  
  #Start backend database container
  docker run --rm --name trading-psql \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_USER=postgres \
  --network trading-net \
  -d -p 5432:5432 vishaysingh35/trading-psql
  
  #Verify
  docker ps
  
  #Set IEX token (Register at IEX Cloud)
  IEX_PUB_TOKEN="YOUR_TOKEN"
  
  #Start backend trading app container
  docker run -d --rm --name trading-app \
  -e "PSQL_HOST=trading-psql" \
  -e "PSQL_PORT=5432" \
  -e "PSQL_USER=postgres" \
  -e "PSQL_DB=jrvstrading" \
  -e "PSQL_PASSWORD=password" \
  -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \
  --network trading-net \
  -p 8080:8080 -t vishaysingh35/trading-app
  
  #Verify
  docker ps
  
  #If you want to stop the backend containers, or start them again, use
  #this simple script
  cd trading-ui/src/
  ./init.sh [start|stop] 
  ```

- Docker Setup (Frontend)
  
  ```sh
  #Pull the docker image
  docker pull vishaysingh35/frontend
  
  #Run the frontend container
  docker run -p 3000:3000 vishaysingh35/frontend
  
  #Verify
  docker ps
  
  #Visit the web-app here: http://localhost:3000
  ```

# Implementation
This project was implemented using React.js for all pages/components/etc., and both SCSS and CSS for styling purposes. `App.js` declares `Router.js`, which defines all the paths and their corresponding pages, like `Dashboard.js` for example. Each page has components such as `NavBar.js` in the `Dashboard.js`. Components are where things like tables are defined. Finally, all constants that are needed throughout the frontend are stored in `Constants.js`, such as the URL for the backend, `BACKEND_URL`. Packages are updated/managed using NPM (`npm install`), and defined in `package.json`. 

## Architecture
![Frontend](https://user-images.githubusercontent.com/56552567/179609085-8e67d3fd-ad9f-4a2d-8dd8-875536d773ea.png)

# Testing
Testing was done primarily in the browser, by testing all pages, components, etc. in the UI. Console logging proved useful for finding various bugs, such as `undefined` errors, which resulted in a more robust frontend application. 

# Deployment
This app was deployed using Docker. A Dockerfile has been provided, making the deployment straightforward:

```sh
docker build -t ${docker_user}/frontend .
```

For steps on how to pull and run the provided docker image(s), please see the [Quick Start](#quick-start).

# Improvements

1. Quote Adding/Removing/Updating - Currently the app only supports reading the quote list, so adding in the functionality to add/remove quotes similarly to the Dashboard page would be beneficial to the UX.

2. Update Dashboard Information - Currently if you try to delete a user that has a balance, the backend simply won't let you, but the frontend does not tell the user why this is happening. Adding this information would clear up why the user cannot perform the deletion.

3. Create Traders Page - You can only access the trader account pages via the specified path that declares the traderId. Adding a page which allows you to select a trader and view their account would be helpful in improving the UX.


