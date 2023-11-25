package com.map.Services.impl;

import com.map.Domain.entities.ArtistEntity;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Repositories.ArtistRepo;
import com.map.Repositories.PlaylistRepo;
import com.map.Services.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepo playlistRepo;

    public PlaylistServiceImpl(PlaylistRepo playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    @Override
    public PlaylistEntity createPlaylist(PlaylistEntity playlistEntity) {
        return playlistRepo.save(playlistEntity);

    }

    @Override
    public List<PlaylistEntity> findAll() {
        return StreamSupport.stream(playlistRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlaylistEntity> findOne(Long id) {
        return playlistRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return playlistRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        playlistRepo.deleteById(id);
    }
}
