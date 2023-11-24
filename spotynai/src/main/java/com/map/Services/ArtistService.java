package com.map.Services;

import com.map.Domain.entities.ArtistEntity;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    ArtistEntity createArtist(ArtistEntity artistEntity);

    List<ArtistEntity> findAll();

    Optional<ArtistEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
