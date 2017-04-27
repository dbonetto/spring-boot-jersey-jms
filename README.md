# spring-boot-jersey-jms
This repository contains my Java POC to make ActiveMQ working with Jersey and Spring Boot.

In order to configure environment, first pull activemq docker image from remote repository:
> docker pull webcenter/activemq

The retrieve imageID from docker image list:
> docker images

Finally run docker container:
> docker run -p 61616:61616 -p 8161:8161 --name activemq <imageID>

You can access administrator web console from:
> http://localhost:8161/admin/index.jsp (user: admin, password: admin)

Message broker is accessible from:
> tcp://localhost:61616