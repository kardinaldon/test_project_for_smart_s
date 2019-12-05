FROM maven:3-jdk-11

ADD target/users-service.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","users-service.jar"]

