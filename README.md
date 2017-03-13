# Spring remote with RabbitMQ example project

### Overview
This a demo project showing how to implement simple distributed Spring application with
remote procedure call over AMQP (RabbitMQ). Full tutorial is available [here](http://molchanoff.me/software-development/spring-remoting-amqp)

This app consists of three modules:
* common - common classes for modules;
* client - client application, which invokes server doJob() method;
* server - server application, which processes UUID generation requests from clients;

### Diagram
![RabbitMQ RPC diagram](http://molchanoff.me/wp-content/uploads/2017/03/RabbitMQ-RPC-diagram.png "RabbitMQ RPC diagram")

### Requirements
* JDK
* Docker

### Build and run
`$ docker-compose up` - this will launch RabbitMQ listening on 5672 port and management UI on 15672 port.

`$ ./gradlew jar` - this will build all modules. Built JAR files will be available in `[module name]/build/libs`
directory.

`$ java -Dnode=1 -jar server/build/libs/server-1.0.jar` - pass `node` JVM property to identify server app.

`$ java -Dclient=1 -jar client/build/libs/client-1.0.jar` - pass `client` JVM property to identify client app.