Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Implementation](#Implementation)
* [Testing](#Testing)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
- The idea behind this project is to replace a legacy trading system, a monolithic application that simply does not scale well, and is hard to manage. This new trading system aims to utilize the microservice architecture and SpringBoot framework to alleviate these issues.
- This is a PoC (proof of concept) so no actual banking information is used, and the scope is much smaller. Namely, this app can create and manage clients/accounts, and execute security orders on stocks defined by IEX Cloud’s REST API.
- All code was written in Java using standard libraries like JdbcTemplate, SpringBoot framework managed dependencies, Swagger created the UI, PostgreSQL was used as the backend database for accounts/traders/security orders/etc., Maven was used to build the project, and finally Docker was used to deploy the project.

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
	-d -p 5432:5432 vishaysingh35/trading-psql

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
	-p 8080:8080 -t vishaysingh35/trading-app

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

	#Then follow the same steps as above (verify using psql cli that all relations were initialized)
	```
- Try the trading-app with SwaggerUI:
  - Visit the webpage on your browser: `http://localhost:8080/swagger-ui.html`:
  
  ![Screenshot 2022-07-14 154508](https://user-images.githubusercontent.com/56552567/179243488-2cccc9b2-632a-4a80-a8b3-1525d2bbc79b.png)
  
  - Demo usage:
  
  ![Screenshot 2022-07-14 154607](https://user-images.githubusercontent.com/56552567/179243621-32b4de04-039b-4e1a-a5d7-cf73bcca3c9d.png)
  
  ![Screenshot 2022-07-14 154641](https://user-images.githubusercontent.com/56552567/179243649-1aba68c1-732a-45ae-8e98-d2ae3550a65b.png)




# Implementation
## Architecture

![SpringBoot_Architecture](https://user-images.githubusercontent.com/56552567/179264453-7aeecefb-b024-4d5a-a49e-bc7bfd5f0937.png)

- Description of components and services: 
  - `Controller layer`: Handles all user requests (HTTP requests) made through the SwaggerUI. This means that all REST API calls made for the trader accounts and quotes are handled by this layer, which calls the corresponding methods in the `Service Layer`. 
  - `Service layer`: This layer handles all business logic related to the corresponding microservices. For example, input validation, checking account balances before transactions, etc. Finally, this layer calls the relevant DAOs in the `Data Access Layer`.
  - `Data Access Layer`: This layer persists and retrieves data from external PostgreSQL databases (CRUD). The DAO design pattern is used to accomplish this with a DAO for each relation. 
  - `SpringBoot`: webservlet/TomCat and IoC SpringBoot is used for IoC (Inversion of Control) dependency injection. Furthermore, it creates and manages the WebServlet container/Apache Tomcat, which hosts the webpage for this app.
  - `PSQL` and `IEX`: PostgreSQL is used for all data persistence on this project. IEX Cloud provides the REST API for all their market data (namely to generate quotes).

## REST API Usage
### Swagger
Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your OpenAPI (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation and client side consumption. We are using it so that we can focus on developing the backend as a PoC, without having to develop a frontend, or use only tools like Postman or Curl.

### Quote Controller
- Description: manages market data read from IEX Cloud’s REST API, persists data to PostgreSQL external database.
- Endpoints:
  - GET `/quote/dailyList`: list all securities that are available for trading in this trading system.
  - GET `/quote/iex/ticker/{ticker}`: get a quote for the specified ticker.
  - POST `/quote/tickerId/{tickerId}`: create a quote with the given tickerId.
  - PUT `/quote/`: put a new quote into the database with a given JSON.
  - PUT `/quote/iexMarketData`: update the database with IEX market data.
### Trader Controller
- Description: manages trader and account information, deposit and withdraw funds from a given account.
- Endpoints:
  	- DELETE `/trader/traderId/{traderId}`: delete the trader with traderId.
	- POST `/trader/`: create a trader with a given JSON.
	- POST `/trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`: create a trader with the given arguments.
	- PUT `/trader/deposit/traderId/{traderId}/amount/{amount}`: deposit money into the given trader’s account.
	- PUT `/trader/withdraw/traderId/{traderId}/amount/{amount}`: withdraw money from the given trader’s account.

# Testing 
Testing was done using JUnit4 for automated integration testing. This testing was done with a >50% code line coverage, and a separate database testing environment was used to ensure separation of production and development. Manual testing was also performed using Postman, and SwaggerUI to test endpoints.

# Deployment

![SpringBoot_Docker](https://user-images.githubusercontent.com/56552567/179293141-09282916-35ee-4af5-b919-940fc6c5bcba.png)

- Description:
	- `Docker CLI`: the commands used in the CLI to build/run/create/etc. the components in `Docker Host`. See `Quick Start`(#Quick-Start) for more information.
	- `Docker Host`:
		- `Docker daemon`: listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes.
		- `Images`: the trading-psql (database image) and trading-app (application image) that provide the volume for the container's instantiation.
		- `Containers`: the trading-psql-dev container for the database, and the trading-app-dev container for the trading application.
		- `Networks`: the trading-net network which both containers use to communicate with one another over port 5432.
	- `Docker Hub`: where both images are pushed to (both the database, and the Java application).

# Improvements
- Improvement 1: `Order Controller`
	-  With more time, I would implement an `Order Controller` that could execute market orders, buy orders, and sell orders. This would give the app more functionality.
- Improvement 2: `DashBoard Controller`
	- By the same token, this controller would provide clients with a detailed way to view trader, account, and portfolio information, thereby expanding functionality and ease of use.
- Improvement 3: Re-Implement DAOs with `JPA/Hibernate`
	- Learning how to use JPA/Hibernate by implementing a DAO with it would be beneficial personally, but it may also provide cleaner, and more robust code.



