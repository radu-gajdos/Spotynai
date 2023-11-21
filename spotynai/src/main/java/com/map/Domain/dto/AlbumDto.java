package com.map.Domain.dto;

import com.map.Domain.entities.ArtistEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AlbumDto {

    private Long id;

    private String title;

    private LocalDate releaseDate;

    private List<SongDto> songDto;

    private ArtistDto artistDto;


}