# CRM_RestAPI_Test

Simple Customer Relationship Management REST API project. . It have 2 entities, Customers and Users (which have different roles)
It implements CRUD operations on both entities and also image management on Customers

## Prerequisites

- Java 1.8+
- Maven 3.x+
- An IDE of your choise (I use Spring Tool Suite)
- Git
- AWS S3 account with a bucket
- AWS RDS account with a DB 

## Getting Started

To run this project locally, perform the following steps.

1. Clone project to your machine using git "git clone https://github.com/LeandroLeon/CRM_RestAPI_Test" 
2. Import the project into your IDE using the maven pom.xml <br/>
  In spring STS this is done by importing an existing maven project
3. In src/main/resources/application.properties you have to add your own credentials of AWS
4. Next try running the app as a Spring Boot app. You can do this by running the RestApiApplication.java


## Funcionality

Note: On the examples below, {entity} should be replaced for "users" or "customers"

### Get Entities (GET_Requests)

#### Get all the instances of an Entity

````
http://localhost:8080/api/{entity}
````

#### Get an instance with the Id

````
http://localhost:8080/api/{entity}/{entityId}
````

### Create a new instance of an Entity (POST_Requests)

You should send a Post request with the Json new instance of the entity

````
http://localhost:8080/api/{entity}
````

#### Customers Example

````
{
  "name" : "CustomerName",
  "surname" : "CustomerSurname"
}
````

#### Users Example

````
{
  "username" : "username",
  "password" : "password",
  "roles" : {
    ["ROLE_ADMIN", "ROLE_USER"]
  }
}
````

### Upload an instance 

````
http://localhost:8080/api/{entity}/{entityId}
````

You should send a Put/Patch request with the Json new instance of the entity

In case of Users you can use both "http verbs" (PATCH or PUT)
However, for customers you are just able to use (PUT)

You can check this article to see the difference: [article](https://www.testingexcellence.com/difference-put-patch-requests/)

The examples for creating new instances works in PUT case.

For PATCH you need to send a Json with the key-value pair(s) you want to update

For example (user entity)
````
{
  "password" : "newPassword"
}
````

> When you want to update a resource with PUT request, 
> you have to send the full payload as the request whereas with PATCH,
> you only send the parameters which you want to update.

### Delete an instance (DELETE_Request)

````
http://localhost:8080/api/{entity}/{entityId}
````

### Images Management

This functionality is only available for customer because It is the entity who have a photo

#### Add a photo to a Customer (POST_Request)

Photos are sent as MultipartFiles with a key ('file') through a form-data

````
http://localhost:8080/api/customers/{customerId}/photo
````

**Take Care** !! If you upload a photo to a user that already have a photo, the first one will be replaced


#### Delete a photo (DELETE_Request)

````
http://localhost:8080/api/customers/{customerId}/photo
````











