# User Service

## Overview
User Service chịu trách nhiệm xử lý các chức năng liên quan đến người dùng, bao gồm:

- Đăng ký người dùng mới

- Đăng nhập (xác thực người dùng)

- Lấy thông tin người dùng theo ID

Đây là một microservice được xây dựng bằng Spring Boot (Java).

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.

## Development
- Các API được định nghĩa trong file `docs/api-specs/user-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8081/users`
- Tham khảo chi tiết API tại `docs/api-specs/user-service.yaml`.