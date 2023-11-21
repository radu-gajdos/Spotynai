package com.map.Domain.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    private MembershipEntity membershipEntity;

    private String userType;
}
