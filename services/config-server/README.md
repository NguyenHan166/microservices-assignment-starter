# Config Server

## Overview
Config Server cung cấp cấu hình tập trung cho toàn bộ hệ thống microservices. Điều này giúp việc thay đổi cấu hình dễ dàng và đồng bộ hơn mà không cần rebuild hay redeploy từng service riêng lẻ.

## Setup
- Service được build từ `Dockerfile` sử dụng OpenJDK 17.
- Mã nguồn chính nằm trong thư mục `src/`.
- Các file cấu hình riêng cho từng service được lưu trong thư mục `src/main/resources/configurations/`.

## Development
- Config Server cung cấp endpoint để các service lấy cấu hình.
- Chạy local bằng lệnh: `docker-compose up --build`

## Endpoints
- Base URL: `http://localhost:8888`