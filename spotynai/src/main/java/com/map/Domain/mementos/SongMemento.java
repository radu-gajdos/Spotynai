package com.map.Domain.mementos;

import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.ArtistEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.SongEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SongMemento {
    private AlbumEntity albumEntityId;
    private List<ArtistEntity> artistEntity;
    private List<GenreEntity> genreEntity;
    private String title;
    private int duration;
    private LocalDate releaseDate;
    private String lyrics;

    public SongMemento(SongEntity songEntity) {
        this.title = songEntity.getTitle();
        this.duration = songEntity.getDuration();
        this.releaseDate = songEntity.getReleaseDate();
        this.lyrics = songEntity.getLyrics();
    }

    public AlbumEntity getAlbumEntityId() {
        return albumEntityId;
    }

    public void setAlbumEntityId(AlbumEntity albumEntityId) {
        this.albumEntityId = albumEntityId;
    }

    public List<ArtistEntity> getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(List<ArtistEntity> artistEntity) {
        this.artistEntity = artistEntity;
    }

    public List<GenreEntity> getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(List<GenreEntity> genreEntity) {
        this.genreEntity = genreEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
