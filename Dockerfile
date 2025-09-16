FROM eclipse-temurin:21
WORKDIR /app
COPY target/backend_uniconnect.jar backend_uniconnect.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend_uniconnect.jar"]
