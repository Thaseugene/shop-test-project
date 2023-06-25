FROM maven:3.6.3-openjdk-17-slim AS build

LABEL maintainer="Eugene Budnichenko <thaseugene@gmail.com>"

WORKDIR /app

# Copy the project files and build the JAR
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the build container
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]

