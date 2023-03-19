# Base image with Java 11 installed
FROM openjdk:11-jdk-slim AS build
COPY build/libs/*.jar hackernews-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","hackernews-api.jar"]