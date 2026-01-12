# Use a Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the code
COPY . .

# ⚠️ CRITICAL FIX: Limit Maven memory usage to prevent crashing on Free Tier
ENV MAVEN_OPTS="-Xmx256m"

# Build the app (skipping tests to save time and memory)
RUN mvn clean package -DskipTests

# Use a lightweight Java image to run the app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
