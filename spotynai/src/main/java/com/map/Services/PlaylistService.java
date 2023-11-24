package com.map.Services;

import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.PlaylistEntity;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    PlaylistEntity createPlaylist(PlaylistEntity playlistEntity);

    List<PlaylistEntity> findAll();

    Optional<PlaylistEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
