# spring-boot-jersey-jms
This repository contains my Java POC to make ActiveMQ working with Jersey and Spring Boot.

In order to configure environment, first pull activemq docker image from remote repository:
> docker pull webcenter/activemq

The retrieve imageID from docker image list:
> docker images

Finally run docker container:
> docker run -p 61616:61616 -p 8161:8161 --name activemq <imageID>

Access web console to create _email-queue_:
> http://localhost:8161/admin/index.jsp (user: admin, pwd: admin)

Message broker is accessible from:
> tcp://localhost:61616