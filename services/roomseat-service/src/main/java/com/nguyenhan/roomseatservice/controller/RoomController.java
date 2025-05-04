package com.nguyenhan.roomseatservice.controller;

import com.nguyenhan.roomseatservice.model.Room;
import com.nguyenhan.roomseatservice.model.RoomRequest;
import com.nguyenhan.roomseatservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roomseats")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public Room addRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.createRoom(roomRequest);
    }
}
