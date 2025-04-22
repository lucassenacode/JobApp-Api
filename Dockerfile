# Etapa 1: Build com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o POM e os sources
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# Build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/jobapi-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação (opcional, mas recomendado)
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
