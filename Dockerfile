FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY src ./src
ENTRYPOINT ["java", "-jar", "/app.jar"]