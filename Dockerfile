# Use the official OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/devops-0.0.1-SNAPSHOT.jar /app/devops-0.0.1-SNAPSHOT.jar

# Expose the port on which your application will listen
EXPOSE 8080

# Set the command to run your application when the container starts
CMD ["java", "-jar", "/app/devops-0.0.1-SNAPSHOT.jar"]
