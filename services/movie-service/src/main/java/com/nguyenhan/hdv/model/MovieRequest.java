package com.nguyenhan.hdv.model;

import lombok.*;

@Data
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class MovieRequest {
    private String title;
    private String genre;
    private int duration;
}
