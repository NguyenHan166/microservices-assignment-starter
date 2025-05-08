# System Architecture

## Overview

Hệ thống được thiết kế là một nền tảng đặt vé xem phim trực tuyến, sử dụng kiến trúc microservices để đảm bảo tính linh hoạt, khả năng mở rộng và bảo trì. Mục đích chính của hệ thống là cung cấp một giải pháp toàn diện cho việc quản lý và đặt vé xem phim, từ việc quản lý người dùng, phim, phòng chiếu đến thanh toán và thông báo.

## System Components

### API Gateway (Port: 8222)
- Đóng vai trò là điểm vào duy nhất cho tất cả các client
- Xử lý routing, load balancing và cross-cutting concerns
- Tích hợp với các service khác thông qua Spring Cloud Gateway
- Quản lý authentication và authorization tập trung

### User Service (Port: 8081)
- Quản lý thông tin người dùng và tài khoản
- Xử lý đăng ký, đăng nhập và quản lý profile
- Tích hợp với MySQL database

### Movie Service (Port: 8083)
- Quản lý thông tin phim, lịch chiếu và suất chiếu
- Xử lý tìm kiếm và lọc phim
- Tích hợp với MySQL database

### Booking Service (Port: 8082)
- Xử lý quy trình đặt vé và quản lý đơn đặt chỗ
- Tích hợp với Payment Service để xử lý thanh toán
- Sử dụng PostgreSQL database

### Payment Service (Port: 8084)
- Xử lý các giao dịch thanh toán
- Tích hợp với các cổng thanh toán bên thứ ba
- Sử dụng PostgreSQL database

### Notification Service (Port: 8085)
- Gửi thông báo cho người dùng qua nhiều kênh
- Hỗ trợ email, SMS và push notification

### Room Seat Service
- Quản lý thông tin phòng chiếu và ghế ngồi
- Xử lý việc chọn và giữ chỗ

## Communication

### Internal Communication
- Các service giao tiếp với nhau thông qua REST APIs
- Sử dụng Spring Cloud OpenFeign cho service-to-service communication
- Service Discovery (Eureka) để quản lý service registry
- Config Server để quản lý cấu hình tập trung

### Networking
- Tất cả các service được kết nối thông qua mạng `spring-cloud-network`
- Docker Compose quản lý các service và network
- Mỗi service có health check endpoint riêng

## Data Flow

### Database Flow
- User Service & Movie Service → MySQL Database
- Booking Service & Payment Service → PostgreSQL Database
- Mỗi service có database riêng để đảm bảo tính độc lập

### External Dependencies
- SMTP: Notification Service sử dụng thư viện SMTP client để gửi email thông báo đến người dùng.

## Diagram

````
System Architecture
├── Client Layer
│   ├── Web Application (React)
│   │   └── Port: 3000
│   └── Mobile Application (React Native)
│       └── Port: 8080
├── API Gateway Layer
│   ├── Spring Cloud Gateway
│   │   └── Port: 8222
│   └── Load Balancer
│       └── Health Check
├── Service Discovery & Config
│   ├── Eureka Server
│   │   └── Port: 8761
│   └── Config Server
│       └── Port: 8888
├── Microservices Layer
│   ├── User Service (Spring Boot)
│   │   ├── Port: 8081
│   │   └── MySQL Database
│   ├── Movie Service (Spring Boot)
│   │   ├── Port: 8083
│   │   └── MySQL Database
│   ├── Booking Service (Spring Boot)
│   │   ├── Port: 8082
│   │   └── PostgreSQL Database
│   ├── Payment Service (Spring Boot)
│   │   ├── Port: 8084
│   │   └── MySQL Database
│   ├── Notification Service (Spring Boot)
│   │   └── Port: 8085
│   └── Room Seat Service (Spring Boot)
│       └── Port: 8086
|       └── PostgreSQL Database
└── External Services
    └── Email Service
        └── SMTP Server
````

### I. Client Layer
**Web Application (React)**
- Giao diện người dùng trên nền web với thiết kế responsive.

- Giao tiếp với API Gateway thông qua các RESTful APIs.

### II. API Gateway Layer
**Spring Cloud Gateway (Port: 8222)**
- Là điểm vào duy nhất cho tất cả các yêu cầu từ phía client.

- Thực hiện định tuyến (routing), cân bằng tải (load balancing), và bảo mật với JWT.

- Hỗ trợ giới hạn tốc độ (rate limiting), ghi log và giám sát hệ thống.

### III. Service Discovery & Configuration
**Eureka Server (Port: 8761)**
- Quản lý đăng ký và khám phá dịch vụ (service registry & discovery) giữa các microservices.

**Config Server (Port: 8888)**
- Cung cấp cấu hình tập trung cho toàn bộ hệ thống.
- Hỗ trợ cấu hình theo từng môi trường (dev, staging, production).

### IV. Microservices Layer
**User Service (Port: 8081)**
- Quản lý thông tin người dùng và xác thực/ủy quyền (authentication & authorization).
- Sử dụng MySQL làm hệ quản trị cơ sở dữ liệu.

**Movie Service (Port: 8083)**
- Quản lý danh sách phim, thể loại, lịch chiếu.
- Lưu trữ dữ liệu trong MySQL.

**Booking Service (Port: 8082)**
- Xử lý quy trình đặt vé, thanh toán.
- Sử dụng PostgreSQL cho lưu trữ giao dịch.

**Payment Service (Port: 8084)**
- Quản lý quy trình thanh toán.
- Tích hợp với các cổng thanh toán bên ngoài (VNPay, Momo).
- Lưu trữ dữ liệu trong PostgreSQL.

**Notification Service (Port: 8085)**
- Gửi thông báo đến người dùng qua email.

**Room Seat Service (Port: 8086)**
- Quản lý sơ đồ phòng chiếu và trạng thái ghế ngồi.

### V. Database Layer
**MySQL Database**
- Lưu trữ thông tin người dùng và phim.
- Sử dụng MySQL Connector/J để kết nối.
- Cấu hình kết nối trong `application.yml` của User Service và Movie Service.
- Sử dụng JPA/Hibernate để tương tác với cơ sở dữ liệu.
**PostgreSQL Database**
- Lưu trữ thông tin đặt vé và thanh toán.
- Sử dụng PostgreSQL JDBC Driver để kết nối.
- Cấu hình kết nối trong `application.yml` của Booking Service và Payment Service.
- Sử dụng Spring Data JPA để tương tác với cơ sở dữ liệu.

### VI. External Services
**Email Service (via SMTP)**:
- Công nghệ: Giao thức SMTP.
- Mô tả: Dịch vụ chịu trách nhiệm gửi email thông báo đến người dùng (ví dụ: xác nhận đặt vé, thông báo giao dịch giả lập thành công/thất bại).
- Tích hợp: Notification Service sẽ sử dụng thư viện client SMTP để kết nối đến SMTP server và gửi email.

## Scalability & Fault Tolerance

### Scalability
- Kiến trúc microservices cho phép scale từng service độc lập
- Docker containerization giúp dễ dàng scale horizontally
- Load balancing thông qua API Gateway
- Service discovery cho phép thêm/xóa service động