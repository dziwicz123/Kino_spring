package com.example.oro2.repo;

import com.example.oro2.cinema.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.oro2.cinema.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoScreening extends JpaRepository<Screening, Long> {
    @Query("SELECT s FROM Screening s WHERE s.hall.id = :hallId")
    List<Screening> findByHallId(@Param("hallId") Long hallId);

    @Query("SELECT s FROM Screening s WHERE s.movie.id = :movieId")
    List<Screening> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT s FROM Screening s WHERE s.movie.title = :title")
    List<Screening> findByMovieTitle(@Param("title") String title);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.reservation.screening.id = :screeningId")
    Long countTicketsByScreeningId(@Param("screeningId") Long screeningId);

    @Query("SELECT DISTINCT u FROM Screening s JOIN s.reservations r JOIN r.user u WHERE s.id = :screeningId")
    List<User> findUsersByScreeningId(@Param("screeningId") Long screeningId);
}
