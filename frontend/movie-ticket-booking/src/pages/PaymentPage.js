// import React, { useState } from 'react';
// import { Container, TextField, Button, Typography } from '@mui/material';
// import { useNavigate , useLocation} from 'react-router-dom';

// const PaymentPage = () => {
//     const location = useLocation();
//     const [cardNumber, setCardNumber] = useState('');
//     const [expirationDate, setExpirationDate] = useState('');
//     const [cvv, setCvv] = useState('');
//     const { movieId, showtimeId, selectedSeats, quantity } = location.state || {};
//     const navigate = useNavigate();

//     const handlePayment = () => {
//         // Xử lý thanh toán
//         navigate('/confirmation' ,{
//             state: { ticket: { movieId: movieId, showtimeId: showtimeId, selectedSeats: selectedSeats, userId: 1, quantity: quantity } },
//           });
//     };

//     return (
//         <Container>
//             <Typography variant="h4" gutterBottom>Payment Information</Typography>
//             <TextField
//                 label="Card Number"
//                 variant="outlined"
//                 fullWidth
//                 value={cardNumber}
//                 onChange={(e) => setCardNumber(e.target.value)}
//                 margin="normal"
//             />
//             <TextField
//                 label="Expiration Date"
//                 variant="outlined"
//                 fullWidth
//                 value={expirationDate}
//                 onChange={(e) => setExpirationDate(e.target.value)}
//                 margin="normal"
//             />
//             <TextField
//                 label="CVV"
//                 variant="outlined"
//                 fullWidth
//                 value={cvv}
//                 onChange={(e) => setCvv(e.target.value)}
//                 margin="normal"
//             />
//             <Button variant="contained" color="primary" onClick={handlePayment} style={{ marginTop: '20px' }}>
//                 Pay Now
//             </Button>
//         </Container>
//     );
// };

// export default PaymentPage;


import React, { useState } from 'react';
import { Container, TextField, Button, Typography, CircularProgress } from '@mui/material';
import { useNavigate, useLocation, useParams } from 'react-router-dom';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8222';

const PaymentPage = () => {
    const location = useLocation();
    const { movieId, showtimeId } = useParams();
    const [cardNumber, setCardNumber] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
    const [cvv, setCvv] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const { selectedSeats, quantity, roomId } = location.state || {};

    const handlePayment = () => {
        // Xử lý thanh toán
        navigate('/confirmation' ,{
            state: { ticket: { movieId: movieId, showtime: showtimeId, selectedSeats: selectedSeats, userId: 1, quantity: quantity } },
          });
    };

    if (!movieId || !showtimeId || !selectedSeats || !quantity || !roomId) {
        return (
            <Container>
                <Typography variant="h6" color="error" align="center">
                    Invalid booking information. Please start over.
                </Typography>
            </Container>
        );
    }

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Payment Information</Typography>
            <Typography variant="h6" gutterBottom>
                Booking Summary:
            </Typography>
            <Typography variant="body1" gutterBottom>
                Number of seats: {quantity}
            </Typography>
            <Typography variant="body1" gutterBottom>
                Total amount: ${quantity * 10.00}
            </Typography>

            <TextField
                label="Card Number"
                variant="outlined"
                fullWidth
                value={cardNumber}
                onChange={(e) => setCardNumber(e.target.value)}
                margin="normal"
                type="number"
                inputProps={{ maxLength: 16 }}
            />
            <TextField
                label="Expiration Date (MM/YY)"
                variant="outlined"
                fullWidth
                value={expirationDate}
                onChange={(e) => setExpirationDate(e.target.value)}
                margin="normal"
                placeholder="MM/YY"
            />
            <TextField
                label="CVV"
                variant="outlined"
                fullWidth
                value={cvv}
                onChange={(e) => setCvv(e.target.value)}
                margin="normal"
                type="number"
                inputProps={{ maxLength: 3 }}
            />

            {error && (
                <Typography variant="body1" color="error" gutterBottom>
                    {error}
                </Typography>
            )}

            <Button
                variant="contained"
                color="primary"
                onClick={handlePayment}
                // disabled={loading || !cardNumber || !expirationDate || !cvv}
                style={{ marginTop: '20px' }}
            >
                {loading ? <CircularProgress size={24} /> : 'Pay Now'}
            </Button>
        </Container>
    );
};

export default PaymentPage;