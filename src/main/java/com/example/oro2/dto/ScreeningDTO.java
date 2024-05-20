package com.example.oro2.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScreeningDTO {
    private Long id;
    private Long hallId;
    private LocalDateTime startTime;
    private Long movieId;
}
