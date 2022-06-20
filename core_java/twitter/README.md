# Introduction
This app aims at providing a backend that can CRUD (Create, Read, Update*, and Delete) tweets using Twitter’s REST API v1. Common design patterns & frameworks were leveraged to design this app, such as MVC architecture, DAOs, and Spring Dependency Injection. It accomplishes this task by using HTTP client, core Java libraries, maven, and SpringBoot. Furthermore, this app is published on DockerHub.

*Note that Twitter does not allow for updating tweets.

# Quick Start
## How to package the app using Maven
- A Dockerfile is already provided, just make sure to be in the `core_java/twitter` directory, and logged into Docker:
```bash
cd core_java/twitter
docker_user=your_docker_id 
docker login -u ${docker_user} --password-stdin 

#Now we can package the app
mvn clean package
```
## How to run the app with Docker
```bash
#To pull the Docker image
docker pull vishaysingh35/twitter

#Usage:
TwitterApp post|show|delete [options] 
#Posting a tweet:
TwitterApp "post" "tweet_text" "latitude:longitude"
#Showing a tweet:
TwitterApp show tweet_id [field1,fields2]
#Deleting a tweet:
TwitterApp delete [id1,id2,..] 

#Example posting a tweet (please set your twitter developer keys):
export consumerKey=YOUR_KEY1
export consumerSecret=YOUR_KEY2
export accessToken=YOUR_KEY3
export tokenSecret=YOUR_KEY4

docker run --rm -e consumerKey=${consumerKey} -e consumerSecret=${consumerSecret} -e accessToken=${accessToken} -e tokenSecret=${tokenSecret} vishaysingh35/twitter post "test post #Docker" "43:79"
```
# Design
## UML diagram
![UML](https://user-images.githubusercontent.com/56552567/174657148-00ca15b4-7a9f-474b-bf24-0ca0640c634c.png)

The architecture for this app is MVC (Model, View, Controller), though this is just the backend, so only the Model and Controller are represented here. Namely, the TwitterCLIApp instantiates the TwitterHelper, TwitterDAO, TwitterService, and TwitterController classes. TwitterController controls the TwitterService, which uses the TwitterDAO. The TwitterDAO is aided by the TwitterHttpHelper to CRUD tweets using Twitter’s REST API v1.0a.

## Models
The Tweet model is based on the model provided by Twitter’s REST API v1.0a, however since that model is significantly large, for simplicity this model is restricted to include only the following attributes:

| **Attribute**    | **Description**                                                                                                                                                                                                                                                                                                 |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `created_at`     | UTC time when this Tweet was created.                                                                                                                                                                                                                                                                           |
| `id`             | The integer representation of the unique identifier for this Tweet. This number is greater than 53 bits and some programming languages may have difficulty/silent defects in interpreting it. Using a signed 64 bit integer for storing this identifier is safe. Use id_str to fetch the identifier to be safe. |
| `id_str`         | The string representation of the unique identifier for this Tweet.                                                                                                                                                                                                                                              |
| `text`           | The actual UTF-8 text of the status update.                                                                                                                                                                                                                                                                     |
| `entities`       | Entities which have been parsed out of the text of the Tweet.                                                                                                                                                                                                                                                   |
| `coordinates`    | Nullable. Represents the geographic location of this Tweet as reported by the user or client application. The inner coordinates array is formatted as geoJSON (longitude first, then latitude).                                                                                                                 |
| `retweet_count`  | Number of times this Tweet has been retweeted.                                                                                                                                                                                                                                                                  |
| `favorite_count` | Nullable. Indicates approximately how many times this Tweet has been liked by Twitter users.                                                                                                                                                                                                                    |
| `favorited`      | Nullable. Indicates whether this Tweet has been liked by the authenticating user.                                                                                                                                                                                                                               |
| `retweeted`      | Indicates whether this Tweet has been Retweeted by the authenticating user.                                                                                                                                                                                                                                     |

## Spring
This project uses SpringBoot to manage dependencies. In the `spring` package, there is `TwitterCLIBean`, `TwitterCLIComponentScan`, and `TwitterCLISpringBoot`. The two former classes were used to implement `@Beans` and `@ComponentScan` approaches to dependency management respectively, however ultimately the TwitterCLIApp was configured using the `TwitterCLISpringBoot` class as the `@SpringBootApplication`. This class automatically configures Spring, and it also implements the `CommandLineRunner` interface, which is how the app is run with the command line arguments mentioned in the `Quick Start` section.

# Testing
How did you test you app using Junit and mockito?
The app was tested using both JUnit and Mockito. JUnit alone was used for integration testing for all major components (`Controller`, `Service`, etc.). Mockito was used in conjunction with JUnit to test the behaviour of those same major classes in unit testing. Testing and implementation was done in the “bottom-up” approach, which also helped to bolster the amount of bugs found (i.e. ironing-out bugs from the bottom up proves to help for later tests that depend on those lower level classes).

## Deployment
This app is deployed using Docker, and a Docker file has been provided. For full steps, see below:
```bash
Cd core_java/twitter

#Packaging
mvn clean package

#Build the docker image
docker build -t ${docker_username}/twitter .

#Check the image exists
docker image ls | grep “twitter”

#Check the Quick Start for running the Docker image
#Finally, push to Docker Hub
docker push ${docker_username}/twitter
```

# Improvements
- Improved Business logic

Improving on the limited business logic would expand the functionality of the app (currently the only business logic relates to text length/basic string validation). 

- Expand the Twitter Tweet model

The current Tweet model is limited for simplicity’s sake, so expansion is necessary to cover the full range of features of Twitter’s REST API.
- Expand functionality

Currently, there is no way to retweet/favorite/reply/etc. so adding relevant arguments to make those calls to the REST API would be nice to have.



