Environment variables for local and production databases:

Local H2
LOCAL_DB_URL=jdbc:h2:mem:local-link;DATABASE_TO_UPPER=false;LOCAL_DB_NAME=admin;LOCAL_DB_PASS=pass;LOCAL_DB_PORT=8085

gradle build
docker build -t app-prod:v1 .
docker run --name link-app -d -p 8888:8080 app-prod:v1