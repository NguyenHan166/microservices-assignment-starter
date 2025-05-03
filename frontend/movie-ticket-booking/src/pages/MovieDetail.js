import React, { useEffect, useState } from 'react';
import { Container, Typography, Grid, Button } from '@mui/material';
import { useParams, Link } from 'react-router-dom';
import { fetchMovieDetails, fetchShowtimes } from '../services/movieService';

const MovieDetail = () => {
    const { movieId } = useParams();
    const [movie, setMovie] = useState(null);
    const [showtimes, setShowtimes] = useState([]);

    useEffect(() => {
        fetchMovieDetails(movieId).then(data => setMovie(data));
        fetchShowtimes(movieId).then(data => setShowtimes(data.showtimes));
    }, [movieId]);

    return (
        <Container>
            {movie && (
                <>
                    <Typography variant="h3">{movie.title}</Typography>
                    <Typography variant="body1" paragraph>{movie.description}</Typography>
                    <Typography variant="h5">Showtimes</Typography>
                    <Grid container spacing={2}>
                        {showtimes.map(showtime => (
                            <Grid item key={showtime.id}>
                                <Link to={`/seats/${movieId}`}>
                                    <Button variant="contained">{showtime.time}</Button>
                                </Link>
                            </Grid>
                        ))}
                    </Grid>
                </>
            )}
        </Container>
    );
};

export default MovieDetail;
