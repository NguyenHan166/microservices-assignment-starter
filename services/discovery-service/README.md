# Discovery Service

## Overview
Discovery Service đóng vai trò là Eureka Server – giúp các microservice khác trong hệ thống đăng ký và phát hiện lẫn nhau. Điều này hỗ trợ khả năng mở rộng và linh hoạt khi triển khai các service trên nhiều node.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Cấu hình Spring Boot và Eureka nằm trong `src/main/resources/application.yml`.

## Development
- Không cung cấp API mà hoạt động như một Eureka registry server.
- Để chạy local, sử dụng lệnh: `docker-compose up --build`

## Endpoints
- Giao diện Eureka Dashboard: `http://localhost:8761`.
- Các service khác sẽ tự động đăng ký và hiển thị tại đây sau khi khởi động.