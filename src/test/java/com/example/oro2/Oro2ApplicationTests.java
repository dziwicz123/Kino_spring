package com.example.oro2;

import com.example.oro2.cinema.*;
import com.example.oro2.dto.*;
import com.example.oro2.repo.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class Oro2ApplicationTests {

    @Autowired
    private RepoMovie repoMovie;

    @Autowired
    private RepoCinema repoCinema;

    @Autowired
    private RepoCinemaHall repoCinemaHall;

    @Autowired
    private RepoReservation repoReservation;

    @Autowired
    private RepoScreening repoScreening;

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoTicket repoTicket;

    private Cinema cinema;

    private CinemaHall cinemaHall1;
    private CinemaHall cinemaHall2;
    private Movie movie1;
    private Movie movie2;
    private Reservation reservation1;
    private Reservation reservation2;
    private Reservation reservation3;
    private Reservation reservation4;
    private Screening screening1;
    private Screening screening2;
    private Screening screening3;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;
    private Ticket ticket4;
    private Ticket ticket5;
    private Ticket ticket6;
    private Ticket ticket7;
    private Ticket ticket8;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setupDatabase(){
        user1 = User.builder().login("John").email("user1@example.com").fullName("John Doe").build();
        user2 = User.builder().login("Jane").email("user2@example.com").fullName("Jane Smith").build();
        user3 = User.builder().login("Alice").email("user3@example.com").fullName("Alice Johnson").build();
        repoUser.save(user1);
        repoUser.save(user2);
        repoUser.save(user3);

        cinema = Cinema.builder().build();
        repoCinema.save(cinema);

        cinemaHall1 = CinemaHall.builder().cinema(cinema).seats(100).build();
        cinemaHall2 = CinemaHall.builder().cinema(cinema).seats(150).build();
        repoCinemaHall.save(cinemaHall1);
        repoCinemaHall.save(cinemaHall2);

        movie1 = Movie.builder().title("Movie Title 1").build();
        movie2 = Movie.builder().title("Movie Title 2").build();
        repoMovie.save(movie1);
        repoMovie.save(movie2);

        screening1 = Screening.builder().hall(cinemaHall1).movie(movie1).startTime(LocalDateTime.of(2024, 4, 24, 12, 0)).build();
        screening2 = Screening.builder().hall(cinemaHall2).movie(movie1).startTime(LocalDateTime.of(2024, 4, 24, 15, 0)).build();
        screening3 = Screening.builder().hall(cinemaHall1).movie(movie2).startTime(LocalDateTime.of(2024, 4, 24, 18, 0)).build();
        repoScreening.save(screening1);
        repoScreening.save(screening2);
        repoScreening.save(screening3);

        reservation1 = Reservation.builder().screening(screening1).user(user1).status(ReservationStatus.PENDING).build();
        reservation2 = Reservation.builder().screening(screening3).user(user2).status(ReservationStatus.CONFIRMED).build();
        reservation3 = Reservation.builder().screening(screening2).user(user3).status(ReservationStatus.CONFIRMED).build();
        reservation4 = Reservation.builder().screening(screening3).user(user1).status(ReservationStatus.PENDING).build();
        repoReservation.save(reservation1);
        repoReservation.save(reservation2);
        repoReservation.save(reservation3);
        repoReservation.save(reservation4);

        ticket1 = Ticket.builder().reservation(reservation1).type(TicketType.STANDARD).build();
        ticket2 = Ticket.builder().reservation(reservation1).type(TicketType.STUDENT).build();
        ticket3 = Ticket.builder().reservation(reservation2).type(TicketType.CHILD).build();
        ticket4 = Ticket.builder().reservation(reservation3).type(TicketType.STANDARD).build();
        ticket5 = Ticket.builder().reservation(reservation3).type(TicketType.DISCOUNTED).build();
        ticket6 = Ticket.builder().reservation(reservation4).type(TicketType.STUDENT).build();
        ticket7 = Ticket.builder().reservation(reservation4).type(TicketType.STANDARD).build();
        ticket8 = Ticket.builder().reservation(reservation4).type(TicketType.CHILD).build();
        repoTicket.save(ticket1);
        repoTicket.save(ticket2);
        repoTicket.save(ticket3);
        repoTicket.save(ticket4);
        repoTicket.save(ticket5);
        repoTicket.save(ticket6);
        repoTicket.save(ticket7);
        repoTicket.save(ticket8);
    }


    @Test
    void getMovieListByCinemaHallId() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MovieDTO> moviePage = repoMovie.findMovieByHall(cinemaHall1.getId(), pageable);
        assertEquals(2, moviePage.getTotalElements());
    }

    @Test
    void findScreeningNumberByCinemaHallId() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ScreeningDTO> screeningPage = repoScreening.findByHallId(cinemaHall1.getId(), pageable);
        assertEquals(2, screeningPage.getTotalElements());
    }

    @Test
    void findScreeningNumberByMovieId() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ScreeningDTO> screeningPage = repoScreening.findByMovieId(movie2.getId(), pageable);
        assertEquals(1, screeningPage.getTotalElements());
    }

    @Test
    void findScreeningsByMovieName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ScreeningDTO> screeningPage = repoScreening.findByMovieTitle(movie2.getTitle(), pageable);
        assertEquals(1, screeningPage.getTotalElements());
    }

    @Test
    void findUsersByScreeningId() {
        List<UserDTO> userList = repoScreening.findUsersByScreeningId(screening3.getId());
        assertEquals(2, userList.size());
    }

    @Test
    void findScreeningsByUserId() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ScreeningDTO> screeningPage = repoUser.findScreeningsByUserId(user1.getId(), pageable);
        assertEquals(2, screeningPage.getTotalElements());
    }

    @Test
    void findScreeningsByUserLogin() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ScreeningDTO> screeningPage = repoUser.findScreeningsByUserLogin(user2.getLogin(), pageable);
        assertEquals(1, screeningPage.getTotalElements());
    }

    @Test
    void getNumberOfViewersByScreeningId() {
        Long viewerNumber = repoScreening.countTicketsByScreeningId(screening3.getId());
        assertEquals(4, viewerNumber);
    }

    @Test
    void getNumberOfCinemaHallsByMovieId() {
        Integer number = repoCinemaHall.countDistinctHallsByMovieId(movie1.getId());
        assertEquals(2, number);
    }

    @Test
    void getNumberOfSeatsByUserInTime() {
        Long userId = user2.getId();
        LocalDateTime start = LocalDateTime.of(2024, 4, 24, 13, 0);
        LocalDateTime end = LocalDateTime.of(2024, 4, 24, 19, 0);
        Integer number = repoUser.countScreeningsByUserIdAndTimeRange(userId, start, end);
        assertEquals(1, number);
    }
}