// import React, { useEffect, useState } from 'react';
// import { Container, Typography, Grid, Button } from '@mui/material';
// import { useParams, Link } from 'react-router-dom';
// import { fetchMovieDetails, fetchShowtimes } from '../services/movieService';

// const MovieDetail = () => {
//     const { movieId } = useParams();
//     const [movie, setMovie] = useState(null);
//     const [showtimes, setShowtimes] = useState([]);

//     useEffect(() => {
//         fetchMovieDetails(movieId).then(data => setMovie(data));
//         fetchShowtimes(movieId).then(data => setShowtimes(data.showtimes));
//     }, [movieId]);

//     return (
//         <Container>
//             {movie && (
//                 <>
//                     <Typography variant="h3">{movie.title}</Typography>
//                     <Typography variant="body1" paragraph>{movie.description}</Typography>
//                     <Typography variant="h5">Showtimes</Typography>
//                     <Grid container spacing={2}>
//                         {showtimes.map(showtime => (
//                             <Grid item key={showtime.id}>
//                                 <Link to={`/seats/${movieId}`}>
//                                     <Button variant="contained">{showtime.time}</Button>
//                                 </Link>
//                             </Grid>
//                         ))}
//                     </Grid>
//                 </>
//             )}
//         </Container>
//     );
// };

// export default MovieDetail;


import React, { useEffect, useState } from 'react';
import { Container, Typography, Grid, Button, CircularProgress } from '@mui/material';
import { useParams, Link } from 'react-router-dom';
import { fetchMovieDetails, fetchShowtimes } from '../services/movieService';

const MovieDetail = () => {
    const { movieId } = useParams();
    const [movie, setMovie] = useState(null);
    const [showtimes, setShowtimes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadMovieData = async () => {
            try {
                setLoading(true);
                const [movieData, showtimesData] = await Promise.all([
                    fetchMovieDetails(movieId),
                    fetchShowtimes(movieId)
                ]);
                setMovie(movieData);
                setShowtimes(showtimesData || []);
                console.log(showtimesData);
                setLoading(false);
            } catch (error) {
                setError("Failed to load movie details");
                console.error(error);
                setLoading(false);
            }
        };
        loadMovieData();
    }, [movieId]);

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

    if (!movie) {
        return (
            <Container>
                <Typography variant="h6" align="center">Movie not found</Typography>
            </Container>
        );
    }

    return (
        <Container>
            <Typography variant="h3">{movie.title}</Typography>
            <Typography variant="body1" paragraph>{movie.description}</Typography>
            <Typography variant="h5">Showtimes</Typography>
            <Grid container spacing={2}>
                {showtimes.map(showtime => (
                    <Grid item key={showtime.id}>
                        <Link to={`/seats/${movieId}`}>
                            <Button variant="contained">{showtime.showtime}</Button>
                        </Link>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default MovieDetail;