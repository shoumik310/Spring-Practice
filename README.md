# Spring Boot
- Modern and recommended way of building Spring Applications
- Uses declarative approach and Cloud native ready
- Requires only JRE to run the application
- Contains embedded tomcat for deploying web applications
- Supports other VM based languages like *Groovy*, *Kotlin* and *Scala*
- Excellent tooling support for development (*dev-tools*)
- Leverages following properties from Spring Framework
  - Spring BOM for dependency management
  - Conditional Configuration for Conditionally loading beans
  - Built in Actuators for getting health check, stats and metrics
  - Configurations can be tuned the `application.properties` file
  
## Project setup
Different ways to set up Spring Boot Project 
- **start.spring.io** - Official website to generate the scaffolding code
- From IDE (Eclipse, IntelliJ Idea)
- From Spring Boot CLI

## Starters dependencies
Spring Boot comes with various starter dependencies
 
1. web - ``spring-boot-starter-web``
2. aop - ``spring-boot-starter-web``
3. jdbc - ``spring-boot-starter-jdbc``
4. jpa - ``spring-boot-starter-jpa``
5. mongodb - ``spring-boot-starter-mongodb`` 

[Complete List of Starter Dependencies](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-starter)

## Packaging Spring Boot applications
Spring boot applications can be deployed with 
- jar
- war

## Running Spring Boot applications
1. Using springboot maven plugin
   ```bash
       spring-boot:run
    ```
    [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/run-mojo.html)
    
    Useful for development
2. As a fat jar
   ```bash
    java -jar helloworld.jar
   ```
   
## Setting up Lombok for generating boilerplate code
1. Add the maven dependency 
```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

```

2. Run the following command 

```bash
java -jar lombok-1.18.12.jar
```

3. Eclipse Users follow the steps    

1. Download the jar from the website - https://projectlombok.org/download
2. Run the jar ``java -jar lombok-1.18.12.jar``
3. Select the installable
4. Close the Eclipse IDE and open it again
5. Clean all the Projects and rebuild the projects
6. Refersh the project

![Lombok installation](https://gitlab.com/classpath-spring-core/spring-boot-examples/-/blob/master/assets/lombok.png)


## Spring Security
