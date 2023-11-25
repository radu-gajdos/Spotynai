package com.map.Controllers;

import com.map.Domain.dto.SongDto;
import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Domain.entities.SongEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.SongRepo;
import com.map.Services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class SongController {
    private Mapper<SongEntity, SongDto> songMapper;
    private SongService songService;

    public SongController(Mapper<SongEntity, SongDto> songMapper, SongService songService) {
        this.songMapper = songMapper;
        this.songService = songService;
    }

    @PostMapping(path = "/songs")
    public SongDto createSong(@RequestBody SongDto songDto) {
        SongEntity songEntity = songMapper.mapFrom(songDto);
        SongEntity savedSongEntity = songService.createSong(songEntity);
        return songMapper.mapTo(savedSongEntity);
    }

    @DeleteMapping(path = "/songs/{id}")
    public ResponseEntity deleteSong(@PathVariable("id") Long id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/songs")
    public List<SongDto> listSonges() {
        List<SongEntity> songs = songService.findAll();
        return songs.stream()
                .map(songMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/songs/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable("id") Long id) {
        Optional<SongEntity> foundSong = songService.findOne(id);
        return foundSong.map(songEntity -> {
            SongDto songDto = songMapper.mapTo(songEntity);
            return new ResponseEntity<>(songDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "songs/{id}")
    public ResponseEntity<SongDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody SongDto songDto) {
        if (!songService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        songDto.setId(id);
        SongEntity songEntity = songMapper.mapFrom(songDto);
        SongEntity savedSongEntity = songService.createSong(songEntity);
        return new ResponseEntity<>(
                songMapper.mapTo(savedSongEntity), HttpStatus.OK);
    }
}
