package com.nguyenhan.roomseatservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class RoomRequest {
    private String name;
    private int seatCount;
}
