FROM eclipse-temurin:21-jdk-alpine
RUN addgroup demogroup; adduser --ingroup demogroup --disabled-password demo
USER demo

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
