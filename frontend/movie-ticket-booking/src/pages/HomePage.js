import React, { useEffect, useState } from 'react';
import { Container, Grid, Typography, TextField } from '@mui/material';
import MovieCard from '../components/MovieCard';
import axios from 'axios';  // Import axios
import { Link } from 'react-router-dom';

const HomePage = () => {
    const [movies, setMovies] = useState([]);
    const [search, setSearch] = useState('');
    const [loading, setLoading] = useState(true); // Thêm trạng thái loading
    const [error, setError] = useState(null); // Thêm trạng thái lỗi

    useEffect(() => {
        // Gọi API để lấy dữ liệu phim từ backend
        axios.get('http://localhost:8222/movies')  // Cập nhật URL API theo backend của bạn
            .then(response => {
                setMovies(response.data); // Cập nhật state với dữ liệu phim
                setLoading(false); // Đặt trạng thái loading thành false
            })
            .catch(error => {
                setError("Failed to fetch movies."); // Xử lý lỗi nếu có
                setLoading(false);
            });
    }, []);

    const handleSearchChange = (event) => {
        setSearch(event.target.value);
    };

    const filteredMovies = movies.filter(movie =>
        movie.title.toLowerCase().includes(search.toLowerCase())
    );

    // Nếu đang tải hoặc có lỗi, hiển thị thông báo
    if (loading) {
        return <Typography variant="h6" align="center">Loading movies...</Typography>;
    }

    if (error) {
        return <Typography variant="h6" align="center" color="error">{error}</Typography>;
    }

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
