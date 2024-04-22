package com.example.oro2.repo;

import com.example.oro2.cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RepoCinema extends JpaRepository<Cinema, Long> {
}
