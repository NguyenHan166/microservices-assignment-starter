# Booking Service

## Overview
Booking Service chịu trách nhiệm xử lý các chức năng liên quan đến việc đặt vé xem phim, bao gồm:
- Tạo yêu cầu đặt vé mới
- Lấy thông tin vé đã đặt theo ID

Dịch vụ này hoạt động như một microservice độc lập, được xây dựng bằng Spring Boot (Java) và tích hợp Kafka để truyền thông giữa các thành phần hệ thống.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Cấu hình Kafka nằm trong `src/main/java/com/nguyenhan/hdv/config/KafkaConfig.java`.

## Development
- Các API được định nghĩa trong file `docs/api-specs/booking-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8082/bookings`
- Tham khảo chi tiết API tại `docs/api-specs/booking-service.yaml`.