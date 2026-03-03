FROM maven:3.9.6-eclipse-temurin-21 AS builder

LABEL maintainer="Daniil Yakubets"
LABEL stage="builder"

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn test-compile -DskipTests -B