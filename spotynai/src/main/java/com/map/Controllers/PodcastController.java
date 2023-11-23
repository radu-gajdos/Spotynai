package com.map.Controllers;

import com.map.Domain.dto.PodcastDto;
import com.map.Domain.entities.PodcastEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.PodcastRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class PodcastController {
    private Mapper<PodcastEntity, PodcastDto> podcastMapper;

    private PodcastRepo podcastRepo;
    // private PodcastService podcastService;


    public PodcastController(Mapper<PodcastEntity, PodcastDto> podcastMapper, PodcastRepo podcastRepo) {
        this.podcastMapper = podcastMapper;
        this.podcastRepo = podcastRepo;
        // this.podcastService = podcastService;
    }

    public PodcastDto createPodcast(PodcastDto podcastDto) {
        PodcastEntity podcastEntity = podcastMapper.mapFrom(podcastDto);
        PodcastEntity savedPodcastEntity = podcastRepo.save(podcastEntity);
        return podcastMapper.mapTo(savedPodcastEntity);
    }

    public PodcastDto deletePodcast(PodcastDto podcastDto) throws InvalidAlgorithmParameterException {
        PodcastEntity podcastEntity = podcastMapper.mapFrom(podcastDto);

        Optional<PodcastEntity> optionalPodcastEntity = podcastRepo.findById(podcastEntity.getId());

        if (optionalPodcastEntity.isPresent()) {
            podcastRepo.delete(optionalPodcastEntity.get());
            return podcastMapper.mapTo(optionalPodcastEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The podcast does not exist!");
        }
    }

    public Iterable<PodcastEntity> readAllPodcasts() {
        return podcastRepo.findAll();
    }

    public void update(PodcastDto podcastDto, Long searchedPodcastId) throws InvalidAlgorithmParameterException {
        Optional<PodcastEntity> optionalPodcastEntity = podcastRepo.findById(searchedPodcastId);

        if (optionalPodcastEntity.isPresent()) {
            PodcastEntity existingPodcastEntity = optionalPodcastEntity.get();
            podcastRepo.save(existingPodcastEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The podcast does not exist!");
        }
    }
}
