package com.nguyenhan.roomseatservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int row;
    private int col;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId")
    @JsonBackReference
    private Room room;

}
