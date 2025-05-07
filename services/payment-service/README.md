# Payment Service

## Overview
Payment Service chịu trách nhiệm xử lý các hoạt động liên quan đến thanh toán, bao gồm:
- Xử lý thanh toán cho đơn đặt vé
- Kiểm tra trạng thái thanh toán của người dùng

Dịch vụ được xây dựng bằng Spring Boot (Java) và giao tiếp với các service khác thông qua Kafka.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Cấu hình Kafka được đặt trong `config/KafkaConfig.java`.

## Development
- Các API được định nghĩa trong file `docs/api-specs/payment-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8084/payments`
- Tham khảo chi tiết API tại `docs/api-specs/payment-service.yaml`.