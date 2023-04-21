# action-system

## To use docker

1. build project with `mvn clean package -DskipTests`
2. copy jar to docker folder `cp target/auction-system-0.0.1-SNAPSHOT.jar src/main/docker`
3. `docker-compose` up in docker folder
4. after changes remember to update the image with `docker rmi docker-spring-boot:latest`

