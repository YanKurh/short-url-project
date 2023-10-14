You should set these environment variables for project configuration:

LOCAL_DB_URL=jdbc:h2:mem:local-link;DATABASE_TO_UPPER=false;
LOCAL_DB_NAME=admin;
LOCAL_DB_PASS=pass;
PROD_DB_URL=jdbc:postgresql://postgres.chn7dhpckyy7.eu-west-3.rds.amazonaws.com:5432/linkdb;
PROD_DB_PASS=prodpass;
PROD_DB_USER=produser;
PR0D_SECURITY_PASS=default;

API DOC = http://localhost:8080/api-docs
SWAGGER UI = http://localhost:8080/swagger-ui/index.html
