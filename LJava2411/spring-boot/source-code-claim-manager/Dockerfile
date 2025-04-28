# Use a Java runtime as base
FROM openjdk:17-jdk-slim
# Set working directory inside container
WORKDIR /app
# Copy jar file into container
COPY target/claim-manager-0.0.1-SNAPSHOT.jar claim-manager-0.0.1-SNAPSHOT.jar
# Expose port if needed
EXPOSE 8080
# Run the jar
ENTRYPOINT ["java", "-jar", "claim-manager-0.0.1-SNAPSHOT.jar"]
