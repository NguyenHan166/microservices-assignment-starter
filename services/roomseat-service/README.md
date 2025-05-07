# RoomSeat Service

## Overview
RoomSeat Service chịu trách nhiệm quản lý thông tin phòng chiếu và ghế ngồi trong rạp phim. Bao gồm:

- Tạo mới và truy xuất thông tin phòng chiếu

- Lưu trữ thông tin ghế ngồi của từng phòng

Đây là một microservice sử dụng Spring Boot và kiến trúc RESTful.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.

## Development
- Các API được định nghĩa trong file `docs/api-specs/roomseat-service.yaml`.
- Chạy local bằng lệnh `docker-compose up --build` từ thư mục gốc của dự án.

## Endpoints
- Base URL: `http://localhost:8086/roomseats`
- Tham khảo chi tiết API tại `docs/api-specs/roomseat-service.yaml`.
