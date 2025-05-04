package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Seat {

    private Long id;
    private int row;
    private int col;
    private String status;

    private Room room;

}
