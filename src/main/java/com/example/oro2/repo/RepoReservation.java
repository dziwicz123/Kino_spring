package com.example.oro2.repo;

import com.example.oro2.cinema.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RepoReservation extends JpaRepository<Reservation, Long> {
}
