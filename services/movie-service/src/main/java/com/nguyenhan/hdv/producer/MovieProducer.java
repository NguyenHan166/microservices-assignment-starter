package com.nguyenhan.hdv.producer;

import com.nguyenhan.hdv.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovieProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public MovieProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Gửi thông tin cập nhật phim hoặc lịch chiếu mới vào Kafka
    public void sendMovieUpdate(Movie movie) {
        kafkaTemplate.send("movie-updates", movie.getId().toString(), movie);
    }
}
