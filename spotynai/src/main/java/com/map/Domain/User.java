package com.map.Domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    private Membership membership;

    private String userType;
}
