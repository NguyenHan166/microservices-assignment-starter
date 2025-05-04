import React, { useState, useEffect } from 'react';
import { Container, Typography, Button, Snackbar, Alert } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const ConfirmationPage = () => {
    // Lấy dữ liệu từ useLocation
    const location = useLocation();
    const navigate = useNavigate();

    // Lưu thông tin vé và trạng thái thanh toán
    const [ticket, setTicket] = useState(location.state?.ticket || {});
    const [loading, setLoading] = useState(false);
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');

    // Kiểm tra nếu không có dữ liệu vé, hiển thị lỗi
    // if (!ticket || Object.keys(ticket).length === 0) {
    //     return (
    //         <Container>
    //             <Typography variant="h4" color="error" gutterBottom>
    //                 No ticket data found!
    //             </Typography>
    //         </Container>
    //     );
    // }

    // Gọi API backend để đặt vé
    const handleBookTicket = async () => {
        setLoading(true);

        const { movieId, showtime, userId, seats, quantity } = ticket; // Giả sử bạn có thông tin này trong ticket

        const bookingData = {
            "movieId": 1,
            "userId": 1,
            "roomId": 1,
            "showtime": "2025-05-05T19:30:00",
            "createdAt": "2025-05-04T10:20:00",
            "seatIds": [
                1,
                2
            ],
            "quantity": 2,
            "totalPrice": 20.00,
            "status": "PENDING",
            "paymentStatus": "PENDING"
        }

        try {
            // Gọi API backend (địa chỉ URL cần thay đổi cho đúng)
            const response = await axios.post('http://localhost:8222/bookings', bookingData);

            // Xử lý kết quả trả về từ API
            if (response.status === 200) {
                console.log('Ticket booked successfully:', response.data);
                setSnackbarMessage('Ticket booked successfully!');
                setOpenSnackbar(true);
            }
        } catch (error) {
            setSnackbarMessage('Failed to book ticket, please try again.');
            setOpenSnackbar(true);
        } finally {
            setLoading(false);
        }
    };

    // Đảm bảo ticket được truyền chính xác khi chuyển từ các trang trước
    // useEffect(() => {
    //     if (!ticket) {
    //         navigate('/');
    //     }
    // }, [ticket, navigate]);

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Ticket Confirmation</Typography>
            <Typography variant="h6">Movie: {ticket.movieId}</Typography>
            <Typography variant="h6">Showtime: {ticket.showtime}</Typography>
            <Typography variant="h6">Seats: {ticket.userId}</Typography>

            {/* Nút đặt vé */}
            <Button
                variant="contained"
                color="primary"
                onClick={handleBookTicket}
                disabled={loading}
                style={{ marginTop: '20px' }}
            >
                {loading ? 'Booking...' : 'Book Ticket'}
            </Button>

            {/* Snackbar thông báo kết quả */}
            <Snackbar
                open={openSnackbar}
                autoHideDuration={6000}
                onClose={() => setOpenSnackbar(false)}
            >
                <Alert onClose={() => setOpenSnackbar(false)} severity="success" sx={{ width: '100%' }}>
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </Container>
    );
};

export default ConfirmationPage;
