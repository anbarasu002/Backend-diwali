# ---------- Stage 1: build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only what the build needs (keeps .env and target/ out of the image)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ---------- Stage 2: run ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.war app.war

# Default port; override with -e PORT=...
EXPOSE 8080

# MONGODB_URI, CORS_ALLOWED_ORIGINS, ADMIN_API_KEY are passed as environment variables at runtime
ENTRYPOINT ["java", "-jar", "app.war"]
