# Movie Service

## Overview
Movie Service chịu trách nhiệm xử lý các chức năng liên quan đến phim, bao gồm:
- Lấy danh sách tất cả các phim
- Lấy thông tin chi tiết của một phim theo ID
- Lấy danh sách các suất chiếu
- Tạo phim mới
- Cập nhật thông tin phim

Đây là một microservice được xây dựng bằng Spring Boot (Java) và đóng vai trò quản lý nội dung phim trong hệ thống đặt vé xem phim.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Cấu hình Kafka nằm trong `src/main/java/com/nguyenhan/hdv/config/KafkaConfig.java`.

## Development
- Các API được định nghĩa trong file `docs/api-specs/movie-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8083/movies`
- Tham khảo chi tiết API tại `docs/api-specs/movie-service.yaml`.