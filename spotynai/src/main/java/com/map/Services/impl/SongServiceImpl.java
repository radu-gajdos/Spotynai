package com.map.Services.impl;

import com.map.Domain.entities.PlaylistEntity;
import com.map.Domain.entities.SongEntity;
import com.map.Repositories.SongRepo;
import com.map.Services.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;

    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public SongEntity createSong(SongEntity songEntity) {
        return songRepo.save(songEntity);

    }

    @Override
    public List<SongEntity> findAll() {
        return StreamSupport.stream(songRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SongEntity> findOne(Long id) {
        return songRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return songRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        songRepo.deleteById(id);
    }
}

