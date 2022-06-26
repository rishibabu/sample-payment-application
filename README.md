## Assignment

Build a small REST API which processes payments. Payments have an id, and they can be approved or canceled through the provided 3rd Party Payment Processing API.

### Requirements

We would like you to implement the following three User Stories:

1. As a User I want to create a payment so that I retrieve an approval code.
2. As a User I want to cancel an existing payment based on a given payment id.
3. As a User I want to search for a payment by a given payment id so that I can see the status of the payment.

### Development

- We expect a solution written in Java (Version >= 11)/Kotlin.
  - Please ask in advance if you want to use another (JVM) language.
- Follow industry standards when building your API (REST principles, think in resources, use proper HTTP verbs, etc.)
- To run the provided 3rd Party Payment Processing API, see the README in ./external-processor-api,
  - Bonus: you can run it easily with the command ` docker-compose up`

### Delivery

- Ideally you provide a *working* solution
  - it's possible to run the application with docker/docker-compose (the reviewer doesn't have to install any additional stuff)
- The payments should be stored in a DB (use docker-compose.yml, otherwise an in-memory DB like H2 is still fine)
- We expect from you to provide a production-ready solution (security, input validation, logs, metrics, etc.), or to describe it how we can achieve.
- Feel free to briefly document your decision process

### Tips

- Keep it simple, clean and easy



## Demo payment process application


   * What kind of architecture was used to achieve the solution?
     - I have used the Springboot Reactive microservice architecture which will actually retrieve the data from an external API(In our case External Payment Process API) non blocking way which is not in traditional rest client.
   * How to test your application?
     - To test our application please consider the below mentioned steps
       - First clone the repositiory using (git clone http://unzer-avuttd@git.codesubmit.io/unzer/connectivity-team-beqwna)
       - This is **java** based Spring boot application which uses **gradle** as a build tool to test you need to download jdk from https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html
       - Set the **Environment** variables for running java command everywhere(refer https://www.ibm.com/docs/en/b2b-integrator/5.2?topic=installation-setting-java-variables-in-windows) or go the jdk bin folder directly and copy the java exe file path then go to the jar file location run the java command
       - Next download the Gradle build tool from https://gradle.org/
       - After setting up java and downloading the gradle then go to our cloned project base location,copy the path eg-> D:\XXX\UnzerRepo\
       - Run the gradle command **gradlew build** 
       - Go to the target folder then open the command prompt run the famous java - jar command **eg java -jar xxx-snaphot.jar**
       - The application will be running in your **localhost** at port **8080**, after it is up and running go to the **Swagger** link http://localhost:8080/swagger-ui/index.html then hit the **try it out** button to test each and every API 
 
   * To keep the  application simple I haven't implemented the security which can be implemented using spring security where token based outh2 validation can be done for each API requests to authorize and authenticate
     - The application can be packaged as either **jar** or **war**.If it needs to be manually deployed in standalone application server like **Tomcat** the war generated from our application can be copied to the **webapps/ROOT**.war folder of the server
     - The recommended and popular way of deployment is using any **CI/CD** tools like **jenkins,aws,azure** etc to continuously integrate the changes commited in any version control application like **git** and build the jar files.Then run the continuous deployment pipeline which run the generated jar files
     - Along with CI/CD another approach which actually scales up the performance of our application by **containerization** which will convert our micro services as docker containers and then can be managed by any docker orchestration tools like **Kubernetes** to manage the cluster
     - Implemented all other mentioned features at a basic level including validation,logging,metrics etc.
     - Used OpenAPI specification to define the standard of the REST API
    