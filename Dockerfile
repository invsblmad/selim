FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/selim.jar /app
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]