package com.map.Domain.entities;

import com.map.Domain.observer.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Arist")

public class ArtistEntity implements Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String name;

    private String bio;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UserEntity> observers;

    @ManyToMany(mappedBy = "artistEntity", fetch = FetchType.EAGER)
    private List<SongEntity> songEntities;

    @Override
    public void addObserver(UserEntity user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(UserEntity user) {
        observers.remove(user);
    }

    @Override
    public void notifyObservers() {
        for (UserEntity observer : this.observers) {
            observer.update(this);
        }
    }

    public void addSong(SongEntity songEntity) {
        notifyObservers();
        this.songEntities.add(songEntity);
    }
}
