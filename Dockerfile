# =============================================================================
# STAGE 1: Build the application using Maven
# =============================================================================
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /attendaceApp

# Copy the Maven POM file first (for better layer caching)
COPY pom.xml .

# Copy the Maven wrapper files if you're using them (optional)
COPY mvnw* ./
COPY .mvn .mvn

# Download dependencies - this layer is cached unless pom.xml changes
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a symbolic link to standardize the path
RUN mkdir -p /app/target && ln -sf /attendaceApp/target/*.war /app/target/app.jar

# =============================================================================
# STAGE 2: Create a minimal runtime image
# =============================================================================
FROM eclipse-temurin:17-jre-alpine

# Add labels for better maintainability
LABEL maintainer="Sanjeev"
LABEL application="spring-boot-app"
LABEL version="1.0"

# Create a non-root user to run the application
RUN addgroup -S spring && adduser -S spring -G spring

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /attendaceApp/target/*.war app.war

# Change ownership to the non-root user
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring

# Set environment variables
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENV SPRING_PROFILES_ACTIVE="production"

# Expose the application port
EXPOSE 9595

# Define health check (adjust path as needed for your application)
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:9595/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Optional command arguments that can be overridden
CMD ["--server.port=9595"]