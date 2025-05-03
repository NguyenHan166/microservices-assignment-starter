import React from 'react';
import { Card, CardContent, CardMedia, Button, Typography } from '@mui/material';
import { Link } from 'react-router-dom';

const MovieCard = ({ movie }) => {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        alt={movie.title}
        height="140"
        image={movie.imageUrl}
      />
      <CardContent>
        <Typography variant="h6">{movie.title}</Typography>
        <Typography variant="body2" color="text.secondary">
          {movie.description}
        </Typography>
      </CardContent>
      <Link to={`/movie/${movie.id}`}>
        <Button size="small">View Details</Button>
      </Link>
    </Card>
  );
};

export default MovieCard;
