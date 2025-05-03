import React, { useState } from 'react';
import { Container, Grid, Button, Typography } from '@mui/material';
import { useNavigate , useParams } from 'react-router-dom';

const SeatSelection = () => {
    const [selectedSeats, setSelectedSeats] = useState([]);
    const { movieId } = useParams();
    const navigate = useNavigate();

    const handleSeatClick = (seatId) => {
        setSelectedSeats(prevSelected =>
            prevSelected.includes(seatId)
                ? prevSelected.filter(id => id !== seatId)
                : [...prevSelected, seatId]
        );
    };

    const handleProceedToPayment = () => {
        navigate(`/payment/${selectedSeats}`, {
            state: { movieId },
          });
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Select Your Seats</Typography>
            <Grid container spacing={1} justifyContent="center">
                {Array.from({ length: 5 }).map((_, rowIndex) => (
                    <Grid item key={rowIndex} xs={12}>
                        <Grid container spacing={1} justifyContent="center">
                            {Array.from({ length: 10 }).map((_, colIndex) => {
                                const seatId = `${rowIndex}-${colIndex}`;
                                return (
                                    <Grid item key={seatId}>
                                        <Button
                                            variant="outlined"
                                            color={selectedSeats.includes(seatId) ? 'primary' : 'default'}
                                            onClick={() => handleSeatClick(seatId)}
                                        >
                                            {seatId}
                                        </Button>
                                    </Grid>
                                );
                            })}
                        </Grid>
                    </Grid>
                ))}
            </Grid>
            <Button variant="contained" color="primary" onClick={handleProceedToPayment} style={{ marginTop: '20px' }}>
                Proceed to Payment
            </Button>
        </Container>
    );
};

export default SeatSelection;
