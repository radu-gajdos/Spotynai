package com.map.Domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Album")

public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Album_id_seq")
    private Long id;

    private String title;

    private LocalDate releaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SongEntity> songEntities;

    @OneToOne
    private ArtistEntity artistEntity;


}
