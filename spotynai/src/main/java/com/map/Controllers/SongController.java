package com.map.Controllers;
;
import com.map.Domain.dto.SongDto;
import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Domain.entities.SongEntity;
import com.map.Domain.mementos.SongMemento;
import com.map.Mappers.Mapper;
import com.map.Services.SongService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class SongController {
    @Autowired
    private  Mapper<SongEntity, SongDto> songMapper;
    @Autowired
    private  SongService songService;
    private Map<Long, SongMemento> songMementos = new HashMap<>();


    @PostMapping(path = "/create_song")
    public SongDto createSong(@RequestBody SongDto songDto) {
        SongEntity songEntity = songMapper.mapFrom(songDto);
        SongEntity savedSongEntity = songService.createSong(songEntity);
//        songMementos.put(songEntity.getId(), songEntity.createMemento());
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

        SongMemento existingMemento = songMementos.get(id);

        if (existingMemento != null) {
            existingMemento = existingSong.createMemento();
        } else {
            existingMemento = new SongMemento(existingSong);
        }

        songMementos.put(id, existingMemento);

        songDto.setId(id);
        ObjectUpdater.updateFields(existingSong, songDto);

        SongEntity savedSongEntity = songService.createSong(existingSong);

        return new ResponseEntity<>(
                songMapper.mapTo(savedSongEntity), HttpStatus.OK);
    }


    @PostMapping(path = "/restore_song/{id}")
    public ResponseEntity<SongDto> restoreSong(@PathVariable("id") Long id) {
        SongEntity songEntity = songService.findOne(id).orElse(null);

        if (songEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SongMemento memento = songMementos.get(id);

        if (memento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        songEntity.restoreFromMemento(memento);

        SongEntity restoredSongEntity = songService.createSong(songEntity);

        return new ResponseEntity<>(songMapper.mapTo(restoredSongEntity), HttpStatus.OK);
    }
}
