FROM openjdk:8
COPY target/person-project.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--db.host=${DB_HOST:mysqldb}", "--db.port=${DB_PORT:3306}", "--db.name=${DB_NAME:person}", "--db.username=${DB_USERNAME:root}", "--db.password=${DB_PASSWORD:12345}"]