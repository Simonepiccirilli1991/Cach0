FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/CACH0-0.0.1-SNAPSHOT.jar /app/CACH0.jar

# Expose the port
EXPOSE 8082

LABEL name="cach-img"

# Run the application
CMD ["java", "-jar", "CACH0.jar"]
