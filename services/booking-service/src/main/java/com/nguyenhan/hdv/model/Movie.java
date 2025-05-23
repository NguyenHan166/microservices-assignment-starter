package com.nguyenhan.hdv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private String genre;
    private int duration;
    List<ShowTime> showTimes;
}
