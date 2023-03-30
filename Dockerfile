FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/selim.jar /app
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app.jar"]