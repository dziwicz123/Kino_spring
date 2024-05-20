package com.example.oro2.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningDTO {
    private Long id;
    private Long hallId;
    private LocalDateTime startTime;
    private Long movieId;
}
