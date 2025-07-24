# Build stage
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build
RUN mkdir /app
WORKDIR /app

# Copy only the pom.xml first to leverage Docker cache
COPY pom.xml .

# Download dependencies (this layer will be cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Final stage
FROM eclipse-temurin:21-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]