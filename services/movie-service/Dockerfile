FROM openjdk:17-jdk-alpine
LABEL maintainer="nguyenhan.com"
WORKDIR /app
COPY target/movie-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]