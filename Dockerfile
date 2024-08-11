# Etapa de construção
FROM maven:3.8.4-eclipse-temurin-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

# Etapa final
FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/target/desafioitau-0.0.1-SNAPSHOT.jar /app/desafioitau.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "desafioitau.jar"]
