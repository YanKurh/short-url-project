Environment variables for local and production databases: 

Local H2
- LOCAL_DB_URL=jdbc:h2:mem:local-link
- LOCAL_DB_NAME=admin
- LOCAL_DB_PASS=pass
- LOCAL_DB_PORT=8085

Production PostgreSQL
- PROD_DB_URL=jdbc:postgresql://postgres.chn7dhpckyy7.eu-west-3.rds.amazonaws.com:5432/linkdb
- PROD_DB_NAME=produser
- PROD_DB_PASS=prodpass

Create image: docker build -t app-prod:v1 . 
Create container: docker run --name link-app -d -p 8888:8080 app-prod:v1