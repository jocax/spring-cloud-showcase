# spring-cloud-showcase
Showcase for spring cloud.

##build
```
 mvn install
```
##start services
The start of the different services will take some time. Watch the console output. The synchronization of the eureka cluster and the configserver uses the default time from spring-cloud.

```
 ./start.sh
```
After start is completed you can inspect services in the [admin UI](http://localhost:7070/#/overview).

Access [simple service with client](http://localhost:8081/rt) using rest template.
Access [simple service with client](http://localhost:8081/feign) using feign.
Access [simple service with client](http://localhost:8081/zuulsimpleservice) using zuul.

Repeat the link URL invocation and you should see that the called service instance changes.

##stop services
```
 ./stop.sh
```
## overview services

Overview of the modules (micro services).

status | module |description  |  port | link
------------- | --------------|------------- | -------------  | -------------
:white_check_mark: | admin1| UI to administrate spring-boot and spring-cloud services.  | 7070  | [admin1](http://localhost:7070)
:white_check_mark: | admin2| UI to administrate spring-boot and spring-cloud services.  | 7071  | [admin1]()
:white_check_mark: | client1| Simple frontend to show access to service using feign, rest template and zuul.   | 8080  | [client1]()
:white_check_mark: | client2| Simple frontend to show access to service using feign, rest template and zuul.   | 8081  | [client2]()
:white_check_mark: | configserver1| UI + configserver to provide configuration entries to service modules.   | 7080  | [configserver1]()
:white_check_mark: | configserver2| UI + configserver to provide configuration entries to service modules.  | 7081  | [configserver2]()
:white_check_mark: | eurekaserver1| Eureka server for services to register itself. | 8761  | [eurekaserver1]()
:white_check_mark: | eurekaserver2| Eureka server for services to register itself.   | 8762  | [eurekaserver2]()
:white_check_mark: | simpleservice1| Simple service to return the name of the instance.   | 8090  | [simpleservice1]()
:white_check_mark: | simpleservice2| Simple service to return the name of the instance.   | 8091  | [simpleservice2]()
:white_check_mark: | simpleservice3| Simple service to return the name of the instance.   | 8092  | [simpleservice3]()

#Links
[spring-boot-admin](https://github.com/codecentric/spring-boot-admin)
[spring-cloud](https://github.com/spring-projects/spring-cloud)
[spring-boot](https://github.com/spring-projects/spring-boot)