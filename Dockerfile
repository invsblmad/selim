FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/selim.jar /app
EXPOSE 8090
CMD ["java","-jar","/app.jar"]