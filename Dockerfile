# Use a Java runtime as a parent image
FROM openjdk:17-jdk-slim
LABEL maintainer="konzerra@gmail.com"
LABEL version="1.0"
LABEL description="selim"
# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file from the target directory into the container at /app
COPY target/selim.jar /app

# Run the command to start the Spring application
CMD ["java", "-jar", "selim.jar"]


EXPOSE 666

# Run the application when the container starts
CMD ["java", "-jar", "selim.jar"]
