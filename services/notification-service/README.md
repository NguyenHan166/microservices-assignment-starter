# Notification Service

## Overview
Notification Service chịu trách nhiệm gửi thông báo (email) đến người dùng sau khi thực hiện các thao tác quan trọng, như đặt vé thành công. Các chức năng chính bao gồm:
- Gửi thông báo dựa trên yêu cầu từ các microservice khác
- Ghi nhận thông tin thông báo đã gửi vào cơ sở dữ liệu

Dịch vụ được xây dựng bằng Spring Boot (Java), sử dụng Kafka để lắng nghe các sự kiện từ các service khác và tích hợp gửi email qua SMTP.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Cấu hình Kafka và email được đặt trong `config/`:
  - `KafkaConfig.java`.
  - `EmailConfiguration.java`

## Development
- Các API được định nghĩa trong file `docs/api-specs/notification-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8085/notifications`
- Tham khảo chi tiết API tại `docs/api-specs/notification-service.yaml`.