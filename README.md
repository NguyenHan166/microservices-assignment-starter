# 🧩 Hệ thống đặt vé xem phim - Microservice Architecture

## Giới thiệu hệ thống
Đây là hệ thống **đặt vé xem phimn** được xây dựng theo kiến trúc **Microservices**. Các dịch vụ trong hệ thống giao tiếp qua OpenFeign Client và Apache Kafka; được điều phối bởi 1 Api Gateway.


---

## Use case: Đặt vé xem phim

### Mục tiêu:
Người dùng có thể thực hiện xem danh sách phim, chọn suất chiếu và đặt vé xem phim. Hệ thống sẽ lưu thông tin vé xem phim và gửi thông báo qua email cho người dùng.

---

## Luồng hoạt động:
- Giả sử người dùng đã đăng nhập vào hệ thống.
- Người dùng chọn phim muốn xem; frontend sẽ gọi Api tới Api Gateway và chuyển hướng về Movie-service để lấy danh sách phim.
- Người dùng xem thông tin chi tiết phim và chọn suất chiếu; frontend sẽ gọi api tới Api Gateway và chuyển hướng đến Movie-service cũng như Roomseat-service để lấy thông tin liên quan.
- Người dùng thực hiện đặt vé; frontend sẽ gọi api tới Api Gateway và chuyển đến Booking-service; Booking-service sẽ gọi đến các service khác để lấy thông tin:

  - Gọi movie-service để lấy thông tin phim
  - Gọi user-service để lấy thông tin người dùng
  - Lắng nghe trạng thái thanh toán từ Payment-service; sau khi thành công, gọi Notification-service gửi email thông báo đặt vé thành công; lưu thông tin vé vào db.

---

## Hướng dẫn cài đặt hệ thống
1. **Yêu cầu**:
   - Cài đặt Docker và Docker Compose
    
2. **Clone this repository**

   ```bash
   git clone https://github.com/jnp2018/mid-project-338326362.git
   cd mid-project-338326362
   ```
3. **Khởi chạy hệ thống**

   ```bash
   docker-compose up --build
   ```
---

## Truy cập vào hệ thống
- **Frontend**: http://localhost:3000
- **Api Gateway**: http://localhost:8222
