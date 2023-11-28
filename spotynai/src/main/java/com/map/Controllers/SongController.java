package com.map.Controllers;
;
import com.map.Domain.dto.SongDto;
import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Domain.entities.SongEntity;
import com.map.Mappers.Mapper;
import com.map.Services.SongService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class SongController {
    @Autowired
    private  Mapper<SongEntity, SongDto> songMapper;
    @Autowired
    private  SongService songService;


    @PostMapping(path = "/create_song")
    public SongDto createSong(@RequestBody SongDto songDto) {
        SongEntity songEntity = songMapper.mapFrom(songDto);
        SongEntity savedSongEntity = songService.createSong(songEntity);
        return songMapper.mapTo(savedSongEntity);
    }

    @DeleteMapping(path = "/delete_song/{id}")
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
        }).orElse (new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_song/{id}")

    public ResponseEntity<SongDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody SongDto songDto) {
        Optional<SongEntity> existingSongOptional = songService.findOne(id);

        if (existingSongOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SongEntity existingSong = existingSongOptional.get();
        songDto.setId(id);

        ObjectUpdater.updateFields(existingSong, songDto);

        SongEntity savedSongEntity = songService.createSong(existingSong);

        return new ResponseEntity<>(
                songMapper.mapTo(savedSongEntity), HttpStatus.OK);
    }
}
