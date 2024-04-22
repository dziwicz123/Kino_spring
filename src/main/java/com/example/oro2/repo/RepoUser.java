package com.example.oro2.repo;

import com.example.oro2.cinema.Screening;
import com.example.oro2.cinema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepoUser extends JpaRepository<User, Long> {
    @Query("SELECT s FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.id = :userId")
    List<Screening> findScreeningsByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.login = :login")
    List<Screening> findScreeningsByUserLogin(@Param("login") String login);

    @Query("SELECT COUNT(s) FROM User u JOIN u.reservations r JOIN r.screening s WHERE u.id = :userId AND s.startTime BETWEEN :startDateTime AND :endDateTime")
    Integer countScreeningsByUserIdAndTimeRange(@Param("userId") Long userId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
