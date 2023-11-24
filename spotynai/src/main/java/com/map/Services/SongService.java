package com.map.Services;

import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.SongEntity;

import java.util.List;
import java.util.Optional;

public interface SongService {
    SongEntity createSong(SongEntity songEntity);

    List<SongEntity> findAll();

    Optional<SongEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
