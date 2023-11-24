package com.map.Services.impl;

import com.map.Domain.entities.AlbumEntity;
import com.map.Repositories.AlbumRepo;
import com.map.Services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepo albumRepo;

    public AlbumServiceImpl(AlbumRepo albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public AlbumEntity createAlbum(AlbumEntity albumEntity) {
        return albumRepo.save(albumEntity);
    }

    @Override
    public List<AlbumEntity> findAll() {
        return StreamSupport.stream(albumRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AlbumEntity> findOne(Long id) {
        return albumRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return albumRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        albumRepo.deleteById(id);
    }
}
