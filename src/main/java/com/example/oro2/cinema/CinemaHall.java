package com.example.oro2.cinema;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cinema_halls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CinemaHall {
    @Id
    @Column(name = "cinema_hall_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    @OneToMany(mappedBy = "hall")
    private List<Screening> screenings;

    private int seats;
}
