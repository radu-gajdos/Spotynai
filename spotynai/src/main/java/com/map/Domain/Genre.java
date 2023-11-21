package com.map.Domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Genre")

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String name;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<Song> songs;

}
