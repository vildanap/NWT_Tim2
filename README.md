# Travel review
> Travel review is microservice based web application that lets end-users to leave reviews with photos for specific locations. 

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Docker](#docker)
* [Status](#status)
* [Inspiration](#inspiration)


## General info
Add more general information about project. What the purpose of the project is? Motivation?

## Screenshots
![Example screenshot](./img/screenshot.png)

## Prerequisites
* JDK 8 
* React 16.3.2
* MySQL 5.7

## Setup

# Database
* Install MySQL locally 
* For each microservice create a database using names below
Identity microservice: database _nwt2_review_
Image microservice: database _nwt2_image_
Review microservice: database _nwt2_review_
Location microservice: database _nwt2_location_


# Backend
rabbitmq server
* Install and run RabbitMQ server locally

eureka-server
* Import project inside IntellJ IDE File -> New -> Project from Existing Sources
* Create executable project (.jar) and run it
```
mvnw clean package
mvnw java -jar \target\nwt2_ms_location-0.0.1-SNAPSHOT.jar
```

For each microservice:
* Import project inside IntellJ IDE File -> New -> Project from Existing Sources
* Setup the _application.properties_ like below
```
spring.jpa.hibernate.ddl-auto=update 
spring.datasource.username=DB_USER
spring.datasource.password=DB_PASSWORD
spring.datasource.driverClassName=com.mysql.jdbc.Driver  
```
Variables:
DB_USER => your local database user (e.g. root)
DB_PASSWORD => local database password (e.g. default for root is an empty string)

* Create executable project (.jar) and run it
```
mvnw clean package
mvnw java -jar \target\nwt2_ms_location-0.0.1-SNAPSHOT.jar
```

### How to check if it is working?
After running microservices, open the database (e.g. via PHPMyAdmin) and see if tables for each microservice have been created.
Open eureka http://localhost:8761/, all microservices should be registered within Eureka.
![microservices_eureka](https://github.com/vildanap/NWT_Tim2/eureka.PNG)

# Frontend
```
npm install
npm start
```
# Docker


## Status


## Inspiration



