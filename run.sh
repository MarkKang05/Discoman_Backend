./gradlew clean
./gradlew build -x test
docker stop discoman_backend_container
docker rmi -f discoman_backend_image
docker rm discoman_backend_container
docker build -t discoman_backend_image .
docker run -d -p 9090:8080 --name discoman_backend_container --net spring-net -e MYSQL_HOST=local-db -e MYSQL_PORT=3306 discoman_backend_image
