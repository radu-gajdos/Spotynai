package com.map.Services;

import com.map.Domain.entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    GenreEntity createGenre(GenreEntity genreEntity);

    List<GenreEntity> findAll();

    Optional<GenreEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
