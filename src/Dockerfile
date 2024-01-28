# Stage 1: Build Stage
FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk maven
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

