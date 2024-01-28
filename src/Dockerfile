FROM ubuntu:latest AS Build
FROM maven:3.8.4-openjdk-17-slim AS build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Production Stage
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/exercicio3-0.0.8-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
