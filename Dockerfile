#------------Build Stage-------------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

#----------Runtime Stage-------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN apk add --no-cache wget

COPY --from=build /app/target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]