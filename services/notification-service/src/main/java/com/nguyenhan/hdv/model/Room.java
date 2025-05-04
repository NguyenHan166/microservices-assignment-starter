package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Room {
    private Long id;
    private String name;
    private int seatCount=6;

    private List<Seat> seats;
}
