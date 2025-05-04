package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);
}
