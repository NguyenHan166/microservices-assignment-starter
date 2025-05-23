package com.nguyenhan.hdv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ShowTime {

    private Long id;
    private LocalDateTime showtime;
    private Movie movie;
}
