# === Build stage ===
FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

# Copy project files
COPY . .

# Make sure Maven wrapper is executable (if present)
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# === Runtime stage ===
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy only the final JAR from build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]