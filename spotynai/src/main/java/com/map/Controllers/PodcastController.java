package com.map.Controllers;

import com.map.Domain.dto.PodcastDto;
import com.map.Domain.dto.PodcastDto;
import com.map.Domain.entities.PodcastEntity;
import com.map.Domain.entities.PodcastEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.PodcastRepo;
import com.map.Services.PodcastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PodcastController {
    private Mapper<PodcastEntity, PodcastDto> podcastMapper;
    private PodcastService podcastService;

    public PodcastController(Mapper<PodcastEntity, PodcastDto> podcastMapper, PodcastService podcastService) {
        this.podcastMapper = podcastMapper;
        this.podcastService = podcastService;
    }

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

    @PutMapping(path = "podcasts/{id}")
    public ResponseEntity<PodcastDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody PodcastDto podcastDto) {
        if (!podcastService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        podcastDto.setId(id);
        PodcastEntity podcastEntity = podcastMapper.mapFrom(podcastDto);
        PodcastEntity savedPodcastEntity = podcastService.createPodcast(podcastEntity);
        return new ResponseEntity<>(
                podcastMapper.mapTo(savedPodcastEntity), HttpStatus.OK);
    }
}
