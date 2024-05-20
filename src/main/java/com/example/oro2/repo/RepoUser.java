package com.example.oro2.repo;

import com.example.oro2.cinema.Screening;
import com.example.oro2.cinema.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RepoUser extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.id = :userId")
    Page<Screening> findScreeningsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT s FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.login = :login")
    Page<Screening> findScreeningsByUserLogin(@Param("login") String login, Pageable pageable);

    @Query("SELECT COUNT(s) FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.id = :userId AND s.startTime BETWEEN :startDateTime AND :endDateTime")
    Integer countScreeningsByUserIdAndTimeRange(@Param("userId") Long userId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
