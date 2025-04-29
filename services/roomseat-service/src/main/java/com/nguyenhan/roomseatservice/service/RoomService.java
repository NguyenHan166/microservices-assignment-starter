package com.nguyenhan.roomseatservice.service;

import com.nguyenhan.roomseatservice.model.Room;
import com.nguyenhan.roomseatservice.model.RoomRequest;
import com.nguyenhan.roomseatservice.model.Seat;
import com.nguyenhan.roomseatservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room getAllRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
    }

    public Room createRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room.setName(roomRequest.getName());
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 2; i++){
            for (int j = 1; j <= 3; j++){
                Seat seat = new Seat();
                seat.setRoom(room);
                seat.setCol(j);
                seat.setRow(i);
                seats.add(seat);
            }
        }
        room.setSeats(seats);
        return roomRepository.save(room);
    }

}
