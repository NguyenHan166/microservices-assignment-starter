package com.nguyenhan.hdv.controller;

import com.nguyenhan.hdv.model.Movie;
import com.nguyenhan.hdv.model.MovieRequest;
import com.nguyenhan.hdv.model.ShowTime;
import com.nguyenhan.hdv.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/showtimes")
    public List<ShowTime> getShowtimes() {
        return movieService.getShowtimes();
    }

    @PostMapping("/createMovie")
    public Movie createMovie(@RequestBody MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setDuration(movieRequest.getDuration());
        movie.setGenre(movieRequest.getGenre());
        movie.setTitle(movieRequest.getTitle());
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Long id ,@RequestBody MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setDuration(movieRequest.getDuration());
        movie.setGenre(movieRequest.getGenre());
        movie.setTitle(movieRequest.getTitle());
        movieService.updateMovie(id, movie);
        return movie;
    }

    @GetMapping("/showtimes/movie/{movieId}")
    public List<ShowTime> getShowtimesByMovieId(@PathVariable Long movieId) {
        return movieService.getShowtimesByMovieId(movieId);
    }
}
