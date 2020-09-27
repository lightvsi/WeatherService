FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/weatherservice.jar weatherservice.jar
ENTRYPOINT ["java","-jar","/weatherservice.jar"]
