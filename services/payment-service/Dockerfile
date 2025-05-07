FROM openjdk:17-jdk-alpine
LABEL maintainer="nguyenhan.com"
WORKDIR /app
COPY target/payment-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]