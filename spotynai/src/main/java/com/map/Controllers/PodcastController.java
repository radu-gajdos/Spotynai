package com.map.Controllers;

import com.map.Domain.dto.PodcastDto;
import com.map.Domain.dto.PodcastDto;
import com.map.Domain.dto.PodcastDto;
import com.map.Domain.entities.PodcastEntity;
import com.map.Domain.entities.PodcastEntity;
import com.map.Domain.entities.PodcastEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.PodcastRepo;
import com.map.Services.PodcastService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:5500")
public class PodcastController {
    @Autowired
    private Mapper<PodcastEntity, PodcastDto> podcastMapper;
    @Autowired
    private PodcastService podcastService;



    @PostMapping(path = "/podcasts")
    public PodcastDto createPodcast(@RequestBody PodcastDto podcastDto) {
        PodcastEntity podcastEntity = podcastMapper.mapFrom(podcastDto);
        PodcastEntity savedPodcastEntity = podcastService.createPodcast(podcastEntity);
        return podcastMapper.mapTo(savedPodcastEntity);
    }

    @DeleteMapping(path = "/podcasts/{id}")
    public ResponseEntity deletePodcast(@PathVariable("id") Long id) {
        podcastService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/podcasts")
    public List<PodcastDto> listPodcastes() {
        List<PodcastEntity> podcasts = podcastService.findAll();
        return podcasts.stream()
                .map(podcastMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/podcasts/{id}")
    public ResponseEntity<PodcastDto> getPodcast(@PathVariable("id") Long id) {
        Optional<PodcastEntity> foundPodcast = podcastService.findOne(id);
        return foundPodcast.map(podcastEntity -> {
            PodcastDto podcastDto = podcastMapper.mapTo(podcastEntity);
            return new ResponseEntity<>(podcastDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_podcast/{id}")

    public ResponseEntity<PodcastDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody PodcastDto podcastDto) {
        Optional<PodcastEntity> existingPodcastOptional = podcastService.findOne(id);

        if (existingPodcastOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PodcastEntity existingPodcast = existingPodcastOptional.get();
        podcastDto.setId(id);

        // Use the ObjectUpdater utility
        ObjectUpdater.updateFields(existingPodcast, podcastDto);

        PodcastEntity savedPodcastEntity = podcastService.createPodcast(existingPodcast);

        return new ResponseEntity<>(
                podcastMapper.mapTo(savedPodcastEntity), HttpStatus.OK);
    }
}
