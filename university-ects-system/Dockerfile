# =====================
# 1. Build stage
# =====================
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached if no changes)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source code and build the application
COPY src ./src
RUN mvn -B -q package -DskipTests

# =====================
# 2. Run stage
# =====================
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set memory-friendly options for containers
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Expose backend port
EXPOSE 8080

# Healthcheck (relies on Spring Boot Actuator)
HEALTHCHECK --interval=10s --timeout=3s --start-period=20s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
