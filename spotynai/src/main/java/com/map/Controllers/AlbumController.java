package com.map.Controllers;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Mappers.Mapper;
import com.map.Services.AlbumService;
import com.map.config.ObjectUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5500")

public class AlbumController {
    private Mapper<AlbumEntity, AlbumDto> albumMapper;
    private AlbumService albumService;

    public AlbumController(Mapper<AlbumEntity, AlbumDto> albumMapper, AlbumService albumService) {
        this.albumMapper = albumMapper;
        this.albumService = albumService;
    }

    @PostMapping(path = "/create_album")
    public AlbumDto createAlbum(@RequestBody AlbumDto albumDto) {
        AlbumEntity albumEntity = albumMapper.mapFrom(albumDto);
        AlbumEntity savedAlbumEntity = albumService.createAlbum(albumEntity);
        return albumMapper.mapTo(savedAlbumEntity);
    }

    @DeleteMapping(path = "/delete_album/{id}")
    public ResponseEntity deleteAlbum(@PathVariable("id") Long id) {
        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/albums")
    public List<AlbumDto> listAlbumes() {
        List<AlbumEntity> albums = albumService.findAll();
        return albums.stream()
                .map(albumMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/album/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("id") Long id) {
        Optional<AlbumEntity> foundAlbum = albumService.findOne(id);
        return foundAlbum.map(albumEntity -> {
            AlbumDto albumDto = albumMapper.mapTo(albumEntity);
            return new ResponseEntity<>(albumDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_album/{id}")

    public ResponseEntity<AlbumDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody AlbumDto albumDto) {
        Optional<AlbumEntity> existingAlbumOptional = albumService.findOne(id);

        if (existingAlbumOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AlbumEntity existingAlbum = existingAlbumOptional.get();
        albumDto.setId(id);

        ObjectUpdater.updateFields(existingAlbum, albumDto);

        AlbumEntity savedAlbumEntity = albumService.createAlbum(existingAlbum);

        return new ResponseEntity<>(
                albumMapper.mapTo(savedAlbumEntity), HttpStatus.OK);
    }

}

