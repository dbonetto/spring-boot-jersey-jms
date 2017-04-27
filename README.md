# spring-boot-jersey-jms
This repository contains my Java POC to make ActiveMQ working with Jersey and Spring Boot.

To make the environment work you have to run the following docker containers:
> docker run -p 61616:61616 -p 8161:8161 --name activemq webcenter/activemq

You can access administrator web console from:
> http://localhost:8161/admin/index.jsp (user: admin, password: admin)

Message broker is accessible from:
> tcp://localhost:61616
