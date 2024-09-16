# Use a base image with OpenJDK
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/clone_ig-0.0.1-SNAPSHOT.jar /app/clone_ig-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/clone_ig-0.0.1-SNAPSHOT.jar"]