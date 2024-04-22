package com.example.oro2.repo;

import com.example.oro2.cinema.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface RepoCinemaHall extends JpaRepository<CinemaHall, Long> {
    @Query("SELECT COUNT(DISTINCT h) FROM CinemaHall h JOIN h.screenings s WHERE s.movie.id = :movieId")
    Integer countDistinctHallsByMovieId(@Param("movieId") Long movieId);
}
