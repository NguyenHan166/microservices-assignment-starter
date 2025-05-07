FROM openjdk:17-jdk-alpine
LABEL maintainer="nguyenhan.com"
RUN apk add --no-cache wget
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]