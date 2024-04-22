package com.example.oro2.repo;

import org.springframework.stereotype.Repository;
import com.example.oro2.cinema.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoTicket extends JpaRepository<Ticket, Long> {
}
