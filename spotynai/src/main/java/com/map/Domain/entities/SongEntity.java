package com.map.Domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Song")
public class SongEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")

    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private AlbumEntity albumEntityId;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<ArtistEntity> artistEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<GenreEntity> genreEntity;

    private String title;

    private int duration;

    private LocalDate releaseDate;

    private String lyrics;

}