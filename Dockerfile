# ----- STAGE 1: build -----
FROM gradle:8.5-jdk21 AS build

WORKDIR /app

COPY . .

# Aseguramos que el wrapper es ejecutable (por si vienes de Windows)
RUN chmod +x gradlew

# Construimos el jar (sin tests para ir más rápido)
RUN ./gradlew clean build -x test

# ----- STAGE 2: run -----
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiamos el jar generado desde el stage de build
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
