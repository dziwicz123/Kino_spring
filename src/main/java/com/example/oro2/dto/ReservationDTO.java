package com.example.oro2.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDTO {
    private Long id;
    private Long screeningId;
    private Long userId;
    private String status;
    private LocalDateTime reservationDate;
    private List<TicketDTO> tickets;
}
