# Stage 1: Build the application using Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# This command compiles both Java and Kotlin files
RUN mvn clean package -DskipTests

# Stage 2: Create the lightweight final image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render will use this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]