package com.example.oro2.cinema;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "screenings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Screening {
    @Id
    @Column(name = "screening_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cinema_hall_id")
    private CinemaHall hall;

    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screening")
    private List<Reservation> reservations;
}
