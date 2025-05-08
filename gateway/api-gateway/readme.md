# Cấu Hình API Gateway

## Tổng Quan

API Gateway đóng vai trò là điểm truy cập chính cho các yêu cầu từ client, thực hiện chuyển tiếp đến các microservices phù hợp. Nó tích hợp Spring Cloud Gateway với cơ chế khám phá dịch vụ để định tuyến yêu cầu động dựa trên định danh dịch vụ (service ID) và triển khai chính sách CORS để đảm bảo truy cập an toàn từ phía client.

## Chi Tiết Cấu Hình

### Cấu Hình Máy Chủ
- **Cổng**: API Gateway hoạt động trên cổng `8222`.

### Cấu Hình Ứng Dụng Spring
- **Tên Ứng Dụng**: `api-gateway`

### Cấu Hình Định Tuyến
API Gateway thiết lập các tuyến định tuyến (routes) để chuyển tiếp yêu cầu đến các microservices dựa trên mẫu đường dẫn (path pattern). Mỗi tuyến được xác định bởi một định danh duy nhất (`id`) và sử dụng URI cân bằng tải (`lb:`) để liên kết với các dịch vụ trong registry.

#### Danh Sách Tuyến Định Tuyến
1. **Dịch Vụ Quản Lý Đặt Vé**
    - **Định Danh**: `booking-service`
    - **URI**: `lb:http://BOOKING-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/bookings/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/bookings/` đến dịch vụ `BOOKING-SERVICE`.

2. **Dịch Vụ Quản Lý Phim**
    - **Định Danh**: `order-service`
    - **URI**: `lb:http://MOVIE-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/movies/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/movies/` đến dịch vụ `MOVIE-SERVICE`.

3. **Dịch Vụ Quản Lý Người Dùng**
    - **Định Danh**: `user-service`
    - **URI**: `lb:http://USER-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/users/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/users/` đến dịch vụ `USER-SERVICE`.

4. **Dịch Vụ Quản Lý Thanh Toán**
    - **Định Danh**: `payment-service`
    - **URI**: `lb:http://PAYMENT-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/payments/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/payments/` đến dịch vụ `PAYMENT-SERVICE`.

5. **Dịch Vụ Quản Lý Thông Báo**
    - **Định Danh**: `notification-service`
    - **URI**: `lb:http://NOTIFICATION-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/notifications/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/notifications/` đến dịch vụ `NOTIFICATION-SERVICE`.

6. **Dịch Vụ Quản Lý Ghế Phòng**
    - **Định Danh**: `roomseat-service`
    - **URI**: `lb:http://ROOMSEAT-SERVICE`
    - **Điều Kiện Đường Dẫn**: `/roomseats/**` - Chuyển tiếp các yêu cầu có đường dẫn bắt đầu bằng `/roomseats/` đến dịch vụ `ROOMSEAT-SERVICE`.

### Cấu Hình CORS
- **Chính Sách CORS Toàn Cục**: Áp dụng cho tất cả các đường dẫn (`[/**]`).
    - **Mẫu Nguồn Gốc Được Phép**: `http://localhost:*` - Cho phép yêu cầu từ mọi cổng localhost, phù hợp với môi trường phát triển.
    - **Phương Thức HTTP Được Phép**: `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS` - Hỗ trợ các phương thức HTTP tiêu chuẩn.
    - **Tiêu Đề Được Phép**: `*` - Cho phép mọi tiêu đề trong yêu cầu.
    - **Hỗ Trợ Thông Tin Xác Thực**: `true` - Cho phép gửi thông tin xác thực (credentials) như cookie hoặc tiêu đề xác thực trong các yêu cầu cross-origin.

