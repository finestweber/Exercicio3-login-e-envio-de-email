# Stage 1: Build Stage
FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk maven
WORKDIR /app
COPY . .
RUN mvn clean install

# Stage 2: Production Stage
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/exercicio3-0.0.7-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]
