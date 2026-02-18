FROM maven:3.9.9-eclipse-temurin-21 AS builder

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src src

RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine

EXPOSE 8080

COPY --from=builder app/target/erp-0.0.1-SNAPSHOT.jar erp-0.0.1-SNAPSHOT.jar

LABEL authors="artur"

ENTRYPOINT ["java", "-jar", "erp-0.0.1-SNAPSHOT.jar"]