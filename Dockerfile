# ========= ETAPA 1: BUILD (MAVEN + JDK) =========
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml
COPY pom.xml .

# Copiamos el código fuente
COPY src ./src

# Compilamos y empaquetamos el .jar (aquí se descargan las dependencias)
RUN mvn clean package -DskipTests


# ========= ETAPA 2: RUNTIME (JRE LIGERO) =========
FROM eclipse-temurin:17-jre

WORKDIR /app

EXPOSE 8084

# Copiamos el JAR generado en la etapa de build
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
