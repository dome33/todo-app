# Use a minimal Java image for productio
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
# *** Update this line with your actual JAR name ***
COPY target/spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar app.jar

# Cloud Run automatically sets the PORT environment variable.
# Spring Boot defaults to 8080, so we listen on that port.
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]