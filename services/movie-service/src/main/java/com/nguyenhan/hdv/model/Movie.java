package com.nguyenhan.hdv.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private int duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    List<ShowTime> showTimes = new ArrayList<>();
}
