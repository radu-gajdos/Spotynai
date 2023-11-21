package com.map.Controllers;

import com.map.Domain.dto.GenreDto;
import com.map.Domain.entities.GenreEntity;
import com.map.Mappers.Mapper;
import com.map.Services.GenreService;

public class GenreController {
    private Mapper<GenreEntity, GenreDto> genreMapper;
    private GenreService genreService;


    public void Controller( Mapper<GenreEntity, GenreDto> genreMapper,  GenreService genreService) {
        this.genreMapper = genreMapper;
        this.genreService = genreService;
    }

    public GenreDto createAlbum(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreService.createGenre(genreEntity);
        return genreMapper.mapTo(savedGenreEntity);
    }
}
