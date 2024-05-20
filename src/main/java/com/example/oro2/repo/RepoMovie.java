package com.example.oro2.repo;

import com.example.oro2.cinema.Movie;
import com.example.oro2.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepoMovie extends JpaRepository<Movie, Long> {

    @Query("SELECT new com.example.oro2.dto.MovieDTO(m.id, m.title) FROM Movie m INNER JOIN m.screenings s WHERE s.hall.id = :hallId")
    Page<MovieDTO> findMovieByHall(@Param("hallId") Long hallId, Pageable pageable);
}
