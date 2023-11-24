package com.map.Services;

import com.map.Domain.entities.AlbumEntity;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    AlbumEntity createAlbum(AlbumEntity albumEntity);

    List<AlbumEntity> findAll();

    Optional<AlbumEntity> findOne(Long id);

    boolean isExists(Long id);


    void delete(Long id);
}
