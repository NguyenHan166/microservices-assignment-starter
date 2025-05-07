# Hướng Dẫn và Mô Tả Frontend cho Ứng Dụng Đặt Vé Xem Phim

Tài liệu này cung cấp hướng dẫn và mô tả chi tiết về cách triển khai giao diện người dùng (frontend) cho một ứng dụng đặt vé xem phim sử dụng **React.js**. Ứng dụng tích hợp với API Gateway đã cấu hình trước đó để hỗ trợ các trường hợp sử dụng (use case) liên quan đến đặt vé xem phim. Giao diện bao gồm các màn hình và chức năng chính, được thiết kế với **React.js**, sử dụng **Tailwind CSS** để tạo kiểu và **Axios** để gọi API.

## Tổng Quan

Ứng Dụng Đặt Vé Xem Phim là một ứng dụng web đơn trang (Single Page Application - SPA) cho phép người dùng:
- Xem danh sách phim đang chiếu.
- Chọn lịch chiếu và phòng chiếu.
- Chọn ghế ngồi.
- Thực hiện thanh toán.
- Nhận thông báo xác nhận đặt vé.

Ứng dụng frontend giao tiếp với API Gateway (chạy trên `http://localhost:8222`) để truy vấn dữ liệu từ các microservices như `MOVIE-SERVICE`, `ROOMSEAT-SERVICE`, `BOOKING-SERVICE`, `PAYMENT-SERVICE`, và `NOTIFICATION-SERVICE`.

## Cấu Trúc Màn Hình và Chức Năng

### 1. Màn Hình Danh Sách Phim (Movie List)
- **Đường dẫn**: `/movies`
- **Chức năng**:
    - Hiển thị danh sách phim đang chiếu (tên phim, thể loại, thời lượng, poster).
    - Cho phép lọc phim theo thể loại hoặc tìm kiếm theo tên.
    - Nhấn vào phim để xem chi tiết và chọn lịch chiếu.
- **API liên quan**:
    - `GET /movies/list` (từ `MOVIE-SERVICE`): Lấy danh sách phim.
- **Giao diện**:
    - Grid layout hiển thị poster phim.
    - Thanh tìm kiếm và bộ lọc thể loại.

### 2. Màn Hình Chi Tiết Phim và Lịch Chiếu (Movie Details & Showtimes)
- **Đường dẫn**: `/movies/:movieId`
- **Chức năng**:
    - Hiển thị thông tin chi tiết phim (mô tả, trailer, đánh giá).
    - Hiển thị danh sách lịch chiếu (ngày, giờ, phòng chiếu).
    - Cho phép chọn lịch chiếu để chuyển sang màn chọn ghế.
- **API liên quan**:
    - `GET /movies/:movieId` (từ `MOVIE-SERVICE`): Lấy chi tiết phim.
    - `GET /roomseats/showtimes?movieId=:movieId` (từ `ROOMSEAT-SERVICE`): Lấy danh sách lịch chiếu.
- **Giao diện**:
    - Phần trên: Poster, tiêu đề, mô tả phim.
    - Phần dưới: Lịch chiếu theo ngày, hiển thị dưới dạng danh sách hoặc bảng.

### 3. Màn Hình Chọn Ghế (Seat Selection)
- **Đường dẫn**: `/bookings/seats/:showtimeId`
- **Chức năng**:
    - Hiển thị sơ đồ ghế của phòng chiếu.
    - Cho phép chọn ghế (hiển thị trạng thái: trống, đã chọn, đã đặt).
    - Xác nhận lựa chọn ghế để chuyển sang thanh toán.
- **API liên quan**:
    - `GET /roomseats/:showtimeId` (từ `ROOMSEAT-SERVICE`): Lấy sơ đồ ghế và trạng thái.
    - `POST /bookings/reserve` (từ `BOOKING-SERVICE`): Đặt tạm thời các ghế đã chọn.
- **Giao diện**:
    - Sơ đồ ghế dạng lưới (grid) với màu sắc khác nhau cho trạng thái ghế.
    - Tóm tắt thông tin lịch chiếu và ghế đã chọn.

### 4. Màn Hình Thanh Toán (Payment)
- **Đường dẫn**: `/payments/:bookingId`
- **Chức năng**:
    - Hiển thị tóm tắt đặt vé (phim, lịch chiếu, ghế, tổng tiền).
    - Cho phép nhập thông tin thanh toán (thẻ tín dụng, ví điện tử).
    - Gửi yêu cầu thanh toán và nhận xác nhận.
- **API liên quan**:
    - `GET /bookings/:bookingId` (từ `BOOKING-SERVICE`): Lấy chi tiết đặt vé.
- **Giao diện**:
    - Biểu mẫu nhập thông tin thanh toán.
    - Tóm tắt đơn hàng bên cạnh.

### 5. Màn Hình Xác Nhận và Thông Báo (Confirmation)
- **Đường dẫn**: `/bookings/confirmation/:bookingId`
- **Chức năng**:
    - Hiển thị thông tin xác nhận đặt vé (mã vé, phim, ghế, thời gian).
    - Gửi thông báo xác nhận qua email hoặc SMS (tùy chọn).
- **API liên quan**:
    - `GET /bookings/:bookingId` (từ `BOOKING-SERVICE`): Lấy thông tin xác nhận.
- **Giao diện**:
    - Thông tin vé dưới dạng thẻ (card).
    - Nút tải vé hoặc gửi lại thông báo.

### 6. Màn Hình Quản Lý Tài Khoản (User Profile)
- **Đường dẫn**: `/users/profile`
- **Chức năng**:
    - Hiển thị thông tin người dùng (tên, email, lịch sử đặt vé).
    - Cho phép cập nhật thông tin cá nhân.
- **API liên quan**:
    - `GET /users/me` (từ `USER-SERVICE`): Lấy thông tin người dùng.
    - `PUT /users/me` (từ `USER-SERVICE`): Cập nhật thông tin.
    - `GET /bookings/history` (từ `BOOKING-SERVICE`): Lấy lịch sử đặt vé.
- **Giao diện**:
    - Biểu mẫu chỉnh sửa thông tin.
    - Danh sách lịch sử đặt vé dạng bảng.

## Cấu Trúc Thư Mục
```
movie-booking-app/
├── public/
├── src/
│   ├── assets/                  # Hình ảnh, video
│   ├── components/              # Các thành phần giao diện (Header, Footer, MovieCard, SeatGrid,...)
│   ├── pages/                   # Các màn hình (MovieList, MovieDetails, SeatSelection, Payment,...)
│   ├── services/                # Hàm gọi API (api.js)
│   ├── App.jsx                  # Định tuyến chính
│   ├── index.css                # CSS toàn cục
│   └── main.jsx                 # Điểm vào ứng dụng
├── package.json
```

## Mã Nguồn Mẫu

#### Tệp `src/App.jsx`
```jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MovieList from './pages/MovieList';
import MovieDetails from './pages/MovieDetails';
import SeatSelection from './pages/SeatSelection';
import Payment from './pages/Payment';
import Confirmation from './pages/Confirmation';
import UserProfile from './pages/UserProfile';
import Header from './components/Header';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/movies" element={<MovieList />} />
        <Route path="/movies/:movieId" element={<MovieDetails />} />
        <Route path="/bookings/seats/:showtimeId" element={<SeatSelection />} />
        <Route path="/payments/:bookingId" element={<Payment />} />
        <Route path="/bookings/confirmation/:bookingId" element={<Confirmation />} />
        <Route path="/users/profile" element={<UserProfile />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

### 5. Chạy Ứng Dụng
- Khởi động ứng dụng:
  ```bash
  npm startstart
  ```
- Truy cập tại `http://localhost:3000` .

### 6. Kiểm Tra
- **Danh sách phim**: Truy cập `/movies` để kiểm tra danh sách phim.
- **Chọn lịch chiếu**: Nhấn vào phim và chọn lịch chiếu.
- **Chọn ghế**: Kiểm tra sơ đồ ghế và chọn ghế trống.
- **Thanh toán**: Nhập thông tin thanh toán giả lập và kiểm tra xác nhận.
- **Thông báo**: Đảm bảo thông báo xác nhận được gửi qua email.
- **Tài khoản**: Xem và cập nhật thông tin người dùng.
