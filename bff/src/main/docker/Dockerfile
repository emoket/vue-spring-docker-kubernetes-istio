# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="insight.emoket@gmail.com"

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Add the application's jar to the container
ADD bff.jar app.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
