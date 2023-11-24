package com.map.Services.impl;

import com.map.Domain.entities.ArtistEntity;
import com.map.Repositories.ArtistRepo;
import com.map.Services.ArtistService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepo artistRepo;

    public ArtistServiceImpl(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    @Override
    public ArtistEntity createArtist(ArtistEntity artistEntity) {
        return artistRepo.save(artistEntity);

    }

    @Override
    public List<ArtistEntity> findAll() {
        return StreamSupport.stream(artistRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArtistEntity> findOne(Long id) {
        return artistRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return artistRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        artistRepo.deleteById(id);
    }
}

