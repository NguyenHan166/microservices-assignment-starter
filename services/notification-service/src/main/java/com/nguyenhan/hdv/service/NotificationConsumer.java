package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.Movie;
import com.nguyenhan.hdv.model.Payment;
import com.nguyenhan.hdv.model.Ticket;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private BookingClient bookingClient;

    // Lắng nghe sự kiện cập nhật phim từ Kafka topic "movie-updates"
    @KafkaListener(topics = "movie-updates", groupId = "notification-group")
    public void listenMovieUpdates(Movie movie) {
        // Gửi thông báo về phim mới hoặc lịch chiếu mới
        System.out.println("New Movie Update: " + movie.getTitle());
        // Gửi thông báo tới người dùng
    }

    // Lắng nghe sự kiện đặt vé thành công từ Kafka topic "booking-created"
    @KafkaListener(topics = "booking-created", groupId = "notification-group")
    public void listenBookingCreated(Booking booking) {
        // Gửi thông báo khi có đơn đặt vé mới
        System.out.println("Booking Created: " + booking.getId());
        try {
            Ticket ticket = bookingClient.getBooking(booking.getId());
            String textHtml = "<!DOCTYPE html>\n" +
                    "<html lang=\"vi\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Vé Xem Phim</title>\n" +
                    "</head>\n" +
                    "<body style=\"margin: 0; padding: 0; font-family: Arial, Helvetica, sans-serif; background-color: #f4f4f4;\">\n" +
                    "    <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"max-width: 600px; margin: 20px auto; background-color: #ffffff; border: 1px solid #e0e0e0;\">\n" +
                    "        <!-- Header -->\n" +
                    "        <tr>\n" +
                    "            <td style=\"background-color: #1a73e8; text-align: center; padding: 20px;\">\n" +
                    "                <h1 style=\"color: #ffffff; margin: 0; font-size: 24px;\">Vé Xem Phim</h1>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        <!-- Body -->\n" +
                    "        <tr>\n" +
                    "            <td style=\"padding: 20px;\">\n" +
                    "                <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "                    <!-- Greeting -->\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"padding-bottom: 20px;\">\n" +
                    "                            <p style=\"margin: 0; font-size: 16px; color: #333333;\">Chào [Tên Khách Hàng],</p>\n" +
                    "                            <p style=\"margin: 10px 0 0; font-size: 16px; color: #333333;\">Cảm ơn bạn đã đặt vé xem phim tại hệ thống của chúng tôi! Dưới đây là thông tin chi tiết về vé của bạn:</p>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <!-- Ticket Details -->\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"padding: 20px 0;\">\n" +
                    "                            <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"8\" style=\"background-color: #f9f9f9; border: 1px solid #e0e0e0; border-radius: 4px;\">\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Tên Phim:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">" + ticket.getMovie().getTitle() + "</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Rạp Chiếu:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">" +ticket.getRoom().getName() + "- [Địa Chỉ Rạp]</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Ngày Chiếu:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">[Ngày Chiếu: "+ ticket.getBooking().getShowtime() +"</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Phòng Chiếu:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">[Phòng Chiếu, ví dụ: Phòng 3]</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Ghế Ngồi:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">[Ghế Ngồi, ví dụ: A12, A13]</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Mã Vé:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">[Mã Vé, ví dụ: TICKET123456]</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Loại Vé:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color老人: #333333;\">[Loại Vé, ví dụ: 2D, 3D, IMAX]</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333; font-weight: bold;\">Tổng Tiền:</td>\n" +
                    "                                    <td style=\"font-size: 16px; color: #333333;\">[Tổng Tiền, ví dụ: 150,000 VNĐ]</td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <!-- QR Code (Optional) -->\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align: center; padding: 20px 0;\">\n" +
                    "                            <p style=\"margin: 0; font-size: 16px; color: #333333;\">Quét mã QR để kiểm tra vé:</p>\n" +
                    "                            <img src=\"[URL_Mã_QR]\" alt=\"Mã QR\" style=\"width: 120px; height: 120px; margin-top: 10px;\">\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <!-- Instructions -->\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"padding: 20px 0;\">\n" +
                    "                            <p style=\"margin: 0; font-size: 16px; color: #333333; font-weight: bold;\">Hướng Dẫn:</p>\n" +
                    "                            <ul style=\"margin: 10px 0 0; padding-left: 20px; font-size: 16px; color: #333333;\">\n" +
                    "                                <li>Vui lòng đến rạp trước giờ chiếu ít nhất 15 phút.</li>\n" +
                    "                                <li>Xuất trình mã vé hoặc mã QR tại quầy để nhận vé giấy.</li>\n" +
                    "                                <li>Vé không thể đổi hoặc hoàn tiền sau khi đặt.</li>\n" +
                    "                            </ul>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <!-- Contact Info -->\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"padding: 20px 0; border-top: 1px solid #e0e0e0;\">\n" +
                    "                            <p style=\"margin: 0; font-size: 14px; color: #333333;\">Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi:</p>\n" +
                    "                            <p style=\"margin: 5px 0; font-size: 14px; color: #333333;\">Email: support@cinema.com | Hotline: 1900 1234</p>\n" +
                    "                            <p style=\"margin: 5px 0; font-size: 14px; color: #333333;\">Website: <a href=\"https://cinema.com\" style=\"color: #1a73e8; text-decoration: none;\">cinema.com</a></p>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                </table>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        <!-- Footer -->\n" +
                    "        <tr>\n" +
                    "            <td style=\"background-color: #f4f4f4; text-align: center; padding: 10px; font-size: 12px; color: #666666;\">\n" +
                    "                <p style=\"margin: 0;\">© 2025 Cinema. All rights reserved.</p>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "</body>\n" +
                    "</html>";
            emailService.sendEmail(ticket.getUser().getEmail(), "Noti", textHtml);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // Gửi thông báo tới người dùng
    }

    // Lắng nghe kết quả thanh toán từ Kafka topic "payment-status"
    @KafkaListener(topics = "payment-status", groupId = "notification-group")
    public void listenPaymentStatus(Payment payment) {
        // Gửi thông báo khi thanh toán thành công hoặc thất bại
        if ("Success".equals(payment.getStatus())) {
            System.out.println("Payment Successful for Booking ID: " + payment.getBookingId());
        } else {
            System.out.println("Payment Failed for Booking ID: " + payment.getBookingId());
        }
    }

}
