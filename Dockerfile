FROM openjdk:17
WORKDIR /app
ADD build/libs/Link-V1.0.0.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]