package com.nguyenhan.userservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class UserRequest {
    private String name;
    private String email;
}
