package com.map.Services;

import com.map.Domain.entities.PodcastEntity;

import java.util.List;
import java.util.Optional;

public interface PodcastService {
    PodcastEntity createPodcast(PodcastEntity podcastEntity);

    List<PodcastEntity> findAll();

    Optional<PodcastEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
