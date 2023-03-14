# Use an official Ubuntu runtime as a parent image
FROM ubuntu:22.04

# Update Ubuntu package manager and install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

LABEL maintainer="konzerra@gmail.com"
LABEL version="1.0"
LABEL description="selim"

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Build the jar file
RUN ./mvnw package

# Make port 666 available to the world outside this container
EXPOSE 666

# Run the application when the container starts
CMD ["java", "-jar", "target/selim.jar"]
