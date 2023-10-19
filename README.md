### **You should set these environment variables for project configuration:**

1. [x] LOCAL_DB_URL=jdbc:h2:mem:local-link;DATABASE_TO_UPPER=false;
2. [x] LOCAL_DB_NAME=admin;
3. [x] LOCAL_DB_PASS=pass;
4. [x] PROD_DB_URL=jdbc:postgresql://production-db.chn7dhpckyy7.eu-west-3.rds.amazonaws.com:5432/linkdb;
5. [x] PROD_DB_PASS=prodpass;
6. [x] PROD_DB_USER=produser;
7. [x] PR0D_SECURITY_PASS=default;
8. [x] DOMAIN=http://localhost:8080/v1/;
9. [x] LOCAL_REDIS_URL=jdbc:redis://localhost:6379/;
10. [x] REDIS_HOST=localhost;
11. [x] REDIS_PORT=6379;
12. [x] JWT_KEY=2f346e86d347447a64dd1223bc9a2b7eeabc4c58aa7b4445bb6ab957a91d0868;

### **Links for OpenAPI 3.0:**

1. [x] API DOC = [http://localhost:8080/url-shortener/v1/api-docs]()
2. [x] SWAGGER UI = [http://localhost:8080/v1/swagger-ui-shortener_url.html]()

### **For local start:**
1. [x] docker-compose -f docker-compose.local.yml up -d
2. [x] Run main ShortUrlProjectApplication.java

### **For production start:**
1. [x] gradlew build
2. [x] docker build -t app-prod:v1 .
3. [x] docker-compose -f docker-compose.prod.yml up -d
