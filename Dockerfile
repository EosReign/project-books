FROM openjdk:18-jdk-alpine

WORKDIR /project-books

ARG JAR_FILE=target/*.jar
COPY .gradle/ .gradle
COPY gradlew build.gradle ./
RUN ./gradlew dependency:resolve

COPY src ./src

ENTRYPOINT ["java", "-jar", "/app.jar"]
CMD ["./gradlew", "spring-boot:run"]