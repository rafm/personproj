# Person project

Docker local commands:

- Create docker person network: docker network create --driver bridge person-network

- Start local MySQL container: docker run --name mysql-person-local -p 3306:3306 --network person-network -e MYSQL_ROOT_PASSWORD=12345 -e MYSQL_DATABASE=person -d mysql

- Build Spring Boot app from Dockerfile: docker build -t rafm/person-project-local .
 
- Start local Spring Boot app container: docker run --name person-project-local -p 8080:8080 --network person-network -e DB_HOST=mysql-person-local -d rafm/person-project-local