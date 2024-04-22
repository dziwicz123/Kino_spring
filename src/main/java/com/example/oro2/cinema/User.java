package com.example.oro2.cinema;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Wither;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String login;

    @Column(nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
