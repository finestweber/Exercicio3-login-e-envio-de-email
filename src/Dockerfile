

# Stage 1: Build Stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Production Stage
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
