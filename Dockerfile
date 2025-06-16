# build
FROM maven:3.9.7-eclipse-temurin-17-alpine as build
RUN apk update && apk upgrade
COPY . .
RUN ./mvnw clean package -DskipTests

# run
FROM eclipse-temurin:17-jdk-alpine
RUN apk update && apk upgrade
WORKDIR /app
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
