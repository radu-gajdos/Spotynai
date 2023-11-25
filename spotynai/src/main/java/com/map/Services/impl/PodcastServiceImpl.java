package com.map.Services.impl;

import com.map.Domain.entities.PlaylistEntity;
import com.map.Domain.entities.PodcastEntity;
import com.map.Repositories.PodcastRepo;
import com.map.Services.PodcastService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepo podcastRepo;

    public PodcastServiceImpl(PodcastRepo podcastRepo) {
        this.podcastRepo = podcastRepo;
    }

    @Override
    public PodcastEntity createEntity(PodcastEntity podcastEntity) {
        return podcastRepo.save(podcastEntity);

    }

    @Override
    public List<PodcastEntity> findAll() {
        return StreamSupport.stream(podcastRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PodcastEntity> findOne(Long id) {
        return podcastRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return podcastRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        podcastRepo.deleteById(id);
    }
}

