FROM openjdk:17-jdk-alpine
LABEL maintainer="nguyenhan.com"
WORKDIR /app
COPY target/booking-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]