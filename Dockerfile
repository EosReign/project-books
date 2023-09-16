FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY pom.xml /build
COPY src/ /build/src
ENTRYPOINT ["java", "-jar", "/app.jar"]