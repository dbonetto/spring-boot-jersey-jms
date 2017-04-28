# spring-boot-jersey-jms

This repository contains my Java POC to make ActiveMQ working with Jersey, Spring Boot and MongoDB database.
To make the environment run you have to run the following docker containers.
Then launch Java application and access Swagger UI web interface from:
> http://localhost/?url=http%3A%2F%2Flocalhost%3A8080%2Fswagger.json

## ActiveMQ

Run container using:
> docker run -d -p 61616:61616 -p 8161:8161 --name activemq webcenter/activemq

Message broker is accessible from:
> tcp://localhost:61616

You can access administrator web console from:
> http://localhost:8161/admin/index.jsp (user: admin, password: admin)

## MongoDB

Run container using:
> docker run -d -p 3000:3000 -p 27017:27017 --name mongodb mongoclient/mongoclient

MongoClient web interface is accessible from:
> http://localhost:3000

## SwaggerUI

Run container using:
> docker run -d -p 80:8080 --name swagger-ui swaggerapi/swagger-ui
