import React, { useState } from 'react';
import { Container, TextField, Button, Typography } from '@mui/material';
import { useNavigate , useLocation} from 'react-router-dom';

const PaymentPage = () => {
    const location = useLocation();
    const [cardNumber, setCardNumber] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
    const [cvv, setCvv] = useState('');
    const [movieId, setMovieId] = useState(location.state?.movieId || {});
    const navigate = useNavigate();

    const handlePayment = () => {
        // Xử lý thanh toán
        navigate('/confirmation' ,{
            state: { ticket: { movieId: movieId, showtime: "", selectedSeats: "", userId: 123, quantity: 1 } },
          });
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Payment Information</Typography>
            <TextField
                label="Card Number"
                variant="outlined"
                fullWidth
                value={cardNumber}
                onChange={(e) => setCardNumber(e.target.value)}
                margin="normal"
            />
            <TextField
                label="Expiration Date"
                variant="outlined"
                fullWidth
                value={expirationDate}
                onChange={(e) => setExpirationDate(e.target.value)}
                margin="normal"
            />
            <TextField
                label="CVV"
                variant="outlined"
                fullWidth
                value={cvv}
                onChange={(e) => setCvv(e.target.value)}
                margin="normal"
            />
            <Button variant="contained" color="primary" onClick={handlePayment} style={{ marginTop: '20px' }}>
                Pay Now
            </Button>
        </Container>
    );
};

export default PaymentPage;
