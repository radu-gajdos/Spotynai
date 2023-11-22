package com.map.Controllers;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.dto.ArtistDto;
import com.map.Domain.entities.ArtistEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.ArtistRepo;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

public class ArtistController {
    private Mapper<ArtistEntity, ArtistDto> artistMapper;

    private ArtistRepo artistRepo;
    // private AlbumService albumService;


    public ArtistController(Mapper<ArtistEntity, ArtistDto> artistMapper, ArtistRepo artistRepo) {
        this.artistMapper = artistMapper;
        this.artistRepo = artistRepo;
        // this.albumService = albumService;
    }

    public ArtistDto createArtist(ArtistDto artistDto) {
        ArtistEntity artistEntity = artistMapper.mapFrom(artistDto);
        ArtistEntity sevedArtistEntity = artistRepo.save(artistEntity);
        return artistMapper.mapTo(sevedArtistEntity);
    }

    public ArtistDto deleteAlbum(ArtistDto artistDto) throws InvalidAlgorithmParameterException {
        ArtistEntity artistEntity = artistMapper.mapFrom(artistDto);

        Optional<ArtistEntity> optionalArtistEntity = artistRepo.findById(artistEntity.getId());

        if (optionalArtistEntity.isPresent()) {
            artistRepo.delete(optionalArtistEntity.get());
            return artistMapper.mapTo(optionalArtistEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The artist does not exist!");
        }
    }

    public Iterable<ArtistEntity> readAllArtist() {
        return artistRepo.findAll();
    }

    public void update(ArtistDto artistDto, Long searcherArtistId) throws InvalidAlgorithmParameterException {
        Optional<ArtistEntity> optionalArtistEntity = artistRepo.findById(searcherArtistId);

        if (optionalArtistEntity.isPresent()) {
            ArtistEntity existingAlbumEntity = optionalArtistEntity.get();
            artistRepo.save(existingAlbumEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The artist does not exist!");
        }
    }
}
