FROM maven:3-jdk-11

ADD target/frontend.jar /app/
WORKDIR /app
EXPOSE 80 8080
ENTRYPOINT ["java","-jar","frontend.jar"]

