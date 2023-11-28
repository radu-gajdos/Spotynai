package com.map.Controllers;

import com.map.Domain.dto.PlaylistDto;
import com.map.Domain.dto.PlaylistDto;
import com.map.Domain.dto.PlaylistDto;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.PlaylistRepo;
import com.map.Services.PlaylistService;
import com.map.Services.PlaylistService;
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
public class PlaylistController {
    @Autowired
    private Mapper<PlaylistEntity, PlaylistDto> playlistMapper;
    @Autowired
    private PlaylistService playlistService;

    public PlaylistController(Mapper<PlaylistEntity, PlaylistDto> playlistMapper, PlaylistService playlistService) {
        this.playlistMapper = playlistMapper;
        this.playlistService = playlistService;
    }

    @PostMapping(path = "/playlists")
    public PlaylistDto createPlaylist(@RequestBody PlaylistDto playlistDto) {
        PlaylistEntity playlistEntity = playlistMapper.mapFrom(playlistDto);
        PlaylistEntity savedPlaylistEntity = playlistService.createPlaylist(playlistEntity);
        return playlistMapper.mapTo(savedPlaylistEntity);
    }

    @DeleteMapping(path = "/playlists/{id}")
    public ResponseEntity deletePlaylist(@PathVariable("id") Long id) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/playlists")
    public List<PlaylistDto> listPlaylistes() {
        List<PlaylistEntity> playlists = playlistService.findAll();
        return playlists.stream()
                .map(playlistMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/playlists/{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@PathVariable("id") Long id) {
        Optional<PlaylistEntity> foundPlaylist = playlistService.findOne(id);
        return foundPlaylist.map(playlistEntity -> {
            PlaylistDto playlistDto = playlistMapper.mapTo(playlistEntity);
            return new ResponseEntity<>(playlistDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_playlist/{id}")

    public ResponseEntity<PlaylistDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody PlaylistDto playlistDto) {
        Optional<PlaylistEntity> existingPlaylistOptional = playlistService.findOne(id);

        if (existingPlaylistOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PlaylistEntity existingPlaylist = existingPlaylistOptional.get();
        playlistDto.setId(id);

        ObjectUpdater.updateFields(existingPlaylist, playlistDto);

        PlaylistEntity savedPlaylistEntity = playlistService.createPlaylist(existingPlaylist);

        return new ResponseEntity<>(
                playlistMapper.mapTo(savedPlaylistEntity), HttpStatus.OK);
    }
}
