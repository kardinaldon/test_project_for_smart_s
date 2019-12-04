FROM openjdk:11

ADD target/auth-service.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth-service.jar"]