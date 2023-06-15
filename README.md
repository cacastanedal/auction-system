# action-system


# Setup database docker build
`docker run --name dev_postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres:13.1-alpine`

## To use docker

1. build project with `mvn clean package -DskipTests`
2. copy jar to docker folder `cp target/auction-system-0.0.1-SNAPSHOT.jar src/main/docker`
3. `docker-compose up` in docker folder
4. after changes remember to update the image with `docker rmi docker-spring-boot:latest`

