FROM eclipse-temurin:25-jdk-alpine

ARG JAR_FILE=build/libs/web-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8087

CMD ["java", "-jar", "/app.jar"]