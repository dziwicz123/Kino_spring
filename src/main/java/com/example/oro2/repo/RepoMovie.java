package com.example.oro2.repo;

import com.example.oro2.cinema.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepoMovie extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT m FROM Movie m INNER JOIN m.screenings s WHERE s.hall.id = :hallId")
    Page<Movie> findMovieByHall(@Param("hallId") Long hallId, Pageable pageable);
}
