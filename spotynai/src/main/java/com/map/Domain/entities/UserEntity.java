package com.map.Domain.entities;
import com.map.Domain.entities.decorator.UserDecorator;
import com.map.Domain.observer.Observer;
import com.map.Domain.observer.Subject;
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

public class UserEntity implements Observer{
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

    private String badge = null;

    @Override
    public void update(Subject subject) {
        if (subject instanceof ArtistEntity artist) {
            System.out.println(username + " received an update: Artist:  " + artist.getName() + " added a new song!");
        }
    }

    public boolean isAdmin() {
        return userType.equals("admin");
    }

    public void setBadge() {
        if (isAdmin()) {
            this.badge = "Admin Badge";
        }
        else {
            this.badge = "Regular User";
        }
    }
}