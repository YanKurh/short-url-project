FROM openjdk:17

ENV PROD_DB_URL jdbc:postgresql://postgres.chn7dhpckyy7.eu-west-3.rds.amazonaws.com:5432/linkdb
ENV PROD_DB_USER produser
ENV PROD_DB_PASS prodpass
ENV PROD_SECURITY_PASS default

WORKDIR /app
ADD build/libs/Link-V1.0.0.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

#gradle build
#docker build -t app-prod:v1 .
#docker run --name link-app -d -p 8081:8080 app-prod:v1