FROM maven:3.9.1-eclipse-temurin-17-alpine as build
WORKDIR /app
COPY / /app
RUN mvn package -DskipTests

FROM amazoncorretto:17-alpine3.17-jdk
COPY --from=build /app/target/auction-system-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]