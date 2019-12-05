FROM maven:3-jdk-11

ADD target/shopping-service.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","shopping-service.jar"]