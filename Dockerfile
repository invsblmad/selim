FROM openjdk:17-jdk-slim
ADD target/selim.jar app.jar
EXPOSE 8090
CMD ["java","-jar","/app.jar"]