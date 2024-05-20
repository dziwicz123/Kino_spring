package com.example.oro2.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private Long screeningId;
    private Long userId;
    private String status;
    private LocalDateTime reservationDate;
    private List<TicketDTO> tickets;
}
