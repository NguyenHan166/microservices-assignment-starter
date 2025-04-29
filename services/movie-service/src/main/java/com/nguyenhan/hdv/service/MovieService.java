package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Movie;
import com.nguyenhan.hdv.model.ShowTime;
import com.nguyenhan.hdv.producer.MovieProducer;
import com.nguyenhan.hdv.repository.MovieRepository;
import com.nguyenhan.hdv.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieProducer movieProducer;
    private final ShowTimeRepository showTimeRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieProducer movieProducer, ShowTimeRepository showTimeRepository) {
        this.movieRepository = movieRepository;
        this.movieProducer = movieProducer;
        this.showTimeRepository = showTimeRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("movie not found"));
    }

    public List<ShowTime> getShowtimes() {
        // Logic to fetch showtimes
        return new ArrayList<>();
    }


    public Movie createMovie(Movie movie) {
        List<ShowTime> showTimes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShowTime showTime = new ShowTime();
            showTime.setMovie(movie);
            showTime.setShowtime(LocalDateTime.now().plusHours(i));
            showTimes.add(showTime);
        }
        movie.setShowTimes(showTimes);
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id , Movie movie) {
        Movie findMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("movie not found"));
        findMovie.setTitle(movie.getTitle());
        findMovie.setDuration(movie.getDuration());
        findMovie.setGenre(movie.getGenre());
        findMovie.setShowTimes(movie.getShowTimes());
        movieRepository.save(findMovie);
        movieProducer.sendMovieUpdate(findMovie);
        return findMovie;
    }


}
