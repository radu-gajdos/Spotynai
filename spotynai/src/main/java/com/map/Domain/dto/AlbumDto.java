package com.map.Domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArtistDto {

    private Long id;

    private String title;

    private LocalDate releaseDate;

    private List<SongDto> songDto;

    private ArtistDto artistDto;


}