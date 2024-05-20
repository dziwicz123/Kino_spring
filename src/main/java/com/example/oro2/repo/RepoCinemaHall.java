package com.example.oro2.repo;

import com.example.oro2.cinema.CinemaHall;
import com.example.oro2.dto.CinemaHallDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepoCinemaHall extends JpaRepository<CinemaHall, Long> {
    @Query("SELECT COUNT(DISTINCT h) FROM CinemaHall h JOIN h.screenings s WHERE s.movie.id = :movieId")
    Integer countDistinctHallsByMovieId(@Param("movieId") Long movieId);
}
