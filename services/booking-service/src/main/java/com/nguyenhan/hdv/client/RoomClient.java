package com.nguyenhan.hdv.client;

import com.nguyenhan.hdv.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "roomseat-service")
public interface RoomClient {
    @GetMapping("/roomseats/{id}")
    Room getRoom(@PathVariable("id") Long id);
}
