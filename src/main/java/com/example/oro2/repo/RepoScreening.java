package com.example.oro2.repo;

import com.example.oro2.cinema.Screening;
import com.example.oro2.dto.ScreeningDTO;
import com.example.oro2.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoScreening extends JpaRepository<Screening, Long> {

    @Query("SELECT new com.example.oro2.dto.ScreeningDTO(s.id, s.hall.id, s.startTime, s.movie.id) FROM Screening s WHERE s.hall.id = :hallId")
    Page<ScreeningDTO> findByHallId(@Param("hallId") Long hallId, Pageable pageable);

    @Query("SELECT new com.example.oro2.dto.ScreeningDTO(s.id, s.hall.id, s.startTime, s.movie.id) FROM Screening s WHERE s.movie.id = :movieId")
    Page<ScreeningDTO> findByMovieId(@Param("movieId") Long movieId, Pageable pageable);

    @Query("SELECT new com.example.oro2.dto.ScreeningDTO(s.id, s.hall.id, s.startTime, s.movie.id) FROM Screening s WHERE s.movie.title = :title")
    Page<ScreeningDTO> findByMovieTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.reservation.screening.id = :screeningId")
    Long countTicketsByScreeningId(@Param("screeningId") Long screeningId);

    @Query("SELECT DISTINCT new com.example.oro2.dto.UserDTO(u.id, u.email, u.login, u.fullName) FROM Screening s JOIN s.reservations r JOIN r.user u WHERE s.id = :screeningId")
    List<UserDTO> findUsersByScreeningId(@Param("screeningId") Long screeningId);
}
