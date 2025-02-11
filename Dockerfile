FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/approvalengineapplication-0.0.1-SNAPSHOT.jar approvalengineapplication-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "approvalengineapplication-0.0.1-SNAPSHOT.jar"]
