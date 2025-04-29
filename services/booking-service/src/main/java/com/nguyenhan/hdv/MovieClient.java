package com.nguyenhan.hdv;

import com.nguyenhan.hdv.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "movie-service")
public interface MovieClient {
    @GetMapping("/movies/{id}")
    Movie getMovieByID(@PathVariable("id") Long id);
}
