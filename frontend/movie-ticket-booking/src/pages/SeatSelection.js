// import React, { useState } from 'react';
// import { Container, Grid, Button, Typography } from '@mui/material';
// import { useNavigate , useParams } from 'react-router-dom';

// const SeatSelection = () => {
//     const [selectedSeats, setSelectedSeats] = useState([]);
//     const { movieId } = useParams();
//     const navigate = useNavigate();

//     const handleSeatClick = (seatId) => {
//         setSelectedSeats(prevSelected =>
//             prevSelected.includes(seatId)
//                 ? prevSelected.filter(id => id !== seatId)
//                 : [...prevSelected, seatId]
//         );
//     };

//     const handleProceedToPayment = () => {
//         navigate(`/payment/${selectedSeats}`, {
//             state: { movieId },
//           });
//     };

//     return (
//         <Container>
//             <Typography variant="h4" gutterBottom>Select Your Seats</Typography>
//             <Grid container spacing={1} justifyContent="center">
//                 {Array.from({ length: 5 }).map((_, rowIndex) => (
//                     <Grid item key={rowIndex} xs={12}>
//                         <Grid container spacing={1} justifyContent="center">
//                             {Array.from({ length: 10 }).map((_, colIndex) => {
//                                 const seatId = `${rowIndex}-${colIndex}`;
//                                 return (
//                                     <Grid item key={seatId}>
//                                         <Button
//                                             variant="outlined"
//                                             color={selectedSeats.includes(seatId) ? 'primary' : 'default'}
//                                             onClick={() => handleSeatClick(seatId)}
//                                         >
//                                             {seatId}
//                                         </Button>
//                                     </Grid>
//                                 );
//                             })}
//                         </Grid>
//                     </Grid>
//                 ))}
//             </Grid>
//             <Button variant="contained" color="primary" onClick={handleProceedToPayment} style={{ marginTop: '20px' }}>
//                 Proceed to Payment
//             </Button>
//         </Container>
//     );
// };

// export default SeatSelection;


import React, { useState, useEffect } from 'react';
import { Container, Grid, Button, Typography, CircularProgress } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { fetchRoomAndSeats } from '../services/movieService';

const SeatSelection = () => {
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [room, setRoom] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { movieId, showtimeId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const loadRoomData = async () => {
            try {
                setLoading(true);
                const roomData = await fetchRoomAndSeats(movieId);
                setRoom(roomData);
                setLoading(false);
            } catch (error) {
                setError("Failed to load room data");
                setLoading(false);
            }
        };
        loadRoomData();
    }, [showtimeId]);

    const handleSeatClick = (seatId) => {
        setSelectedSeats(prevSelected =>
            prevSelected.includes(seatId)
                ? prevSelected.filter(id => id !== seatId)
                : [...prevSelected, seatId]
        );
    };

    const handleProceedToPayment = () => {
        if (selectedSeats.length === 0) {
            setError("Please select at least one seat");
            return;
        }
        
        navigate(`/payment/${movieId}/${showtimeId}`, {
            state: { 
                movieId,
                showtimeId,
                selectedSeats,
                quantity: selectedSeats.length,
                roomId: room?.id
            },
        });
    };

    if (loading) {
        return (
            <Container style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <CircularProgress />
            </Container>
        );
    }

    if (error) {
        return (
            <Container>
                <Typography variant="h6" color="error" align="center">{error}</Typography>
            </Container>
        );
    }

    if (!room) {
        return (
            <Container>
                <Typography variant="h6" align="center">Room not found</Typography>
            </Container>
        );
    }

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Select Your Seats</Typography>
            <Typography variant="h6" gutterBottom>Room: {room.name}</Typography>
            <Grid container spacing={2}>
                {room.seats.map((seat) => (
                    <Grid item key={seat.id}>
                        <Button
                            variant="outlined"
                            color={seat.status === 'booked' ? 'error' : selectedSeats.includes(seat.id) ? 'primary' : 'default'}
                            onClick={() => seat.status !== 'booked' && handleSeatClick(seat.id)}
                            disabled={seat.status === 'booked'}
                        >
                            {seat.id}
                        </Button>
                    </Grid>
                ))}
            </Grid>
            <Button
                variant="contained"
                color="primary"
                onClick={handleProceedToPayment}
                disabled={selectedSeats.length === 0}
                style={{ marginTop: '20px' }}
            >
                Proceed to Payment ({selectedSeats.length} seats selected)
            </Button>
        </Container>
    );
};

export default SeatSelection;