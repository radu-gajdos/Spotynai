package com.map.Domain.dto;

import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.ArtistEntity;
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

    private List<ArtistDto> artistDto;

    private List<GenreDto> genreDto;

    private String title;

    private int duration;

    private LocalDate releaseDate;

    private String lyrics;

}
