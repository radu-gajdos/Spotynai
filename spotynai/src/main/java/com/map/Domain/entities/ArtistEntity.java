package com.map.Domain.entities;

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

public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String name;

    private String bio;

    @ManyToMany(mappedBy = "artistEntity", fetch = FetchType.EAGER)
    private List<SongEntity> songEntities;
}
