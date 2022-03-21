#### Clean Architecture
- Domain defines all domain models, UserGateway is for major logic data logics.
  major for how to persistent data to data repository
- Persistent keeps all data repository implementations, models and configuration
- UseCases keep all use cases in this case, just create user and load all users
- Web all web components, such as controller, RPC service, and others
- Application all common services such as IDGenerator, EventSourceServices...
- Kafka used to output event source 

In this case, we are using MongoDB as persistent data source

##### When get MVC compatable with Gateway 

```shell script
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                </exclusion>
                <!--<exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-webflux</artifactId>
                </exclusion>-->
                <exclusion>
                    <groupId>org.apache.tomcat.embed</groupId>
                    <artifactId>tomcat-embed-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
<!--        <dependency>-->
<!--            <groupId>org.springframework.boot</groupId>-->
<!--            <artifactId>spring-boot-starter-web</artifactId>-->
<!--        </dependency>-->
```

#####  In this app need to set header with 
head x-customerId == test

Using data folder to start
