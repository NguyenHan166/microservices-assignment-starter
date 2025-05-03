import React, { useEffect, useState } from 'react';
import { Container, Grid, Typography, TextField } from '@mui/material';
import MovieCard from '../components/MovieCard';
import { fetchMovies } from '../services/movieService';
import { Link } from 'react-router-dom';

const HomePage = () => {
    const [movies, setMovies] = useState([]);
    const [search, setSearch] = useState('');

    useEffect(() => {
        fetchMovies().then(data => setMovies(data));
    }, []);

    const handleSearchChange = (event) => {
        setSearch(event.target.value);
    };

    const filteredMovies = movies.filter(movie =>
        movie.title.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <Container>
            <Typography variant="h3" align="center" gutterBottom>Welcome to Movie Booking</Typography>
            <TextField
                label="Search Movies"
                variant="outlined"
                fullWidth
                value={search}
                onChange={handleSearchChange}
                margin="normal"
            />
            <Grid container spacing={4} justifyContent="center">
                {filteredMovies.map(movie => (
                    <Grid item key={movie.id}>
                        <Link to={`/movie/${movie.id}`}>
                            <MovieCard movie={movie} />
                        </Link>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default HomePage;
