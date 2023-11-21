package com.map.Controllers;

import com.map.Domain.dto.ArtistDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.ArtistEntity;
import com.map.Mappers.Mapper;
import com.map.Services.ArtistService;

public class ArtistController {
    private Mapper<ArtistEntity, ArtistDto> artistMapper;
    private ArtistService artistService;


    public void Controller( Mapper<ArtistEntity, ArtistDto> artistMapper,  ArtistService artistService) {
        this.artistMapper = artistMapper;
        this.artistService = artistService;
    }

    public ArtistDto createAlbum(ArtistDto artistDto) {
        ArtistEntity artistEntity = artistMapper.mapFrom(artistDto);
        ArtistEntity savedArtistEntity = artistService.createArtist(artistEntity);
        return artistMapper.mapTo(savedArtistEntity);
    }
}
