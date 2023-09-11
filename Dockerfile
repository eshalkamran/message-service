# Use the official Maven-based image with Java 17 for building the JAR file
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src directory (which includes the source code)
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn package -DskipTests

# Use the official OpenJDK 17 image as the base image for running the JAR file
FROM openjdk:17

# Copy the JAR file from the builder image
COPY --from=builder /app/target/message-service-0.0.1-SNAPSHOT.jar message-service.jar

# Expose the service on port 8081
EXPOSE 8081

# Command to run the JAR file
CMD ["java", "-jar", "message-service.jar"]