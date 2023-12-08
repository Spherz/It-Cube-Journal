FROM maven:3.6.3-jdk-8-slim
WORKDIR /code

COPY . .

CMD ["./mvnw", "spring-boot:run"]