package com.map.Domain.dto;

import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SongDto {

    private Long id;

    private AlbumEntity albumEntityId;

    private List<ArtistEntity> artistEntity;

    private List<GenreEntity> genreEntity;

    private String title;

    private int duration;

    private LocalDate releaseDate;

    private String lyrics;

}
