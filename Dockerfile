FROM openjdk:17

ENV PROD_DB_URL jdbc:postgresql://production-db.chn7dhpckyy7.eu-west-3.rds.amazonaws.com:5432/linkdb
ENV PROD_DB_USER produser
ENV PROD_DB_PASS prodpass
ENV PROD_SECURITY_PASS default
ENV REDIS_HOST=redis
ENV REDIS_PORT=6379
ENV DOMAIN=http://localhost:8080/v1/
ENV JWT_KEY=2f346e86d347447a64dd1223bc9a2b7eeabc4c58aa7b4445bb6ab957a91d0868

WORKDIR /app
ADD build/libs/Link-V1.0.0.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

#gradle build
#docker build -t app-prod:v1 .
#docker run --name link-app -d -p 8080:8080 app-prod:v1