package com.map.Services.impl;

import com.map.Domain.entities.ArtistEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Repositories.ArtistRepo;
import com.map.Repositories.GenreRepo;
import com.map.Services.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GenreServiceImpl implements GenreService {
    private final GenreRepo genreRepo;

    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public GenreEntity createGenre(GenreEntity genreEntity) {
        return genreRepo.save(genreEntity);

    }

    @Override
    public List<GenreEntity> findAll() {
        return StreamSupport.stream(genreRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GenreEntity> findOne(Long id) {
        return genreRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return genreRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        genreRepo.deleteById(id);
    }
}
