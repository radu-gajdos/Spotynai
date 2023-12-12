package com.map.Domain.entities;

import com.map.Domain.mementos.SongMemento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<ArtistEntity> artistEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<GenreEntity> genreEntity;

    private String title;

    private int duration;

    private LocalDate releaseDate;

    private String lyrics;

    public SongMemento createMemento() {
        return new SongMemento(this);
    }

    public void restoreFromMemento(SongMemento memento) {
        this.albumEntityId = memento.getAlbumEntityId();
        this.artistEntity = new ArrayList<>(memento.getArtistEntity());
        this.genreEntity = new ArrayList<>(memento.getGenreEntity());
        this.title = memento.getTitle();
        this.duration = memento.getDuration();
        this.releaseDate = memento.getReleaseDate();
        this.lyrics = memento.getLyrics();
    }

}
