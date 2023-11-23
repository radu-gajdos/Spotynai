package com.map.Controllers;

import com.map.Domain.dto.PlaylistDto;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.PlaylistRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class PlaylistController {
    private Mapper<PlaylistEntity, PlaylistDto> playlistMapper;

    private PlaylistRepo playlistRepo;
    // private PlaylistService playlistService;


    public PlaylistController(Mapper<PlaylistEntity, PlaylistDto> playlistMapper, PlaylistRepo playlistRepo) {
        this.playlistMapper = playlistMapper;
        this.playlistRepo = playlistRepo;
        // this.playlistService = playlistService;
    }

    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {
        PlaylistEntity playlistEntity = playlistMapper.mapFrom(playlistDto);
        PlaylistEntity savedPlaylistEntity = playlistRepo.save(playlistEntity);
        return playlistMapper.mapTo(savedPlaylistEntity);
    }

    public PlaylistDto deletePlaylist(PlaylistDto playlistDto) throws InvalidAlgorithmParameterException {
        PlaylistEntity playlistEntity = playlistMapper.mapFrom(playlistDto);

        Optional<PlaylistEntity> optionalPlaylistEntity = playlistRepo.findById(playlistEntity.getId());

        if (optionalPlaylistEntity.isPresent()) {
            playlistRepo.delete(optionalPlaylistEntity.get());
            return playlistMapper.mapTo(optionalPlaylistEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The playlist does not exist!");
        }
    }

    public Iterable<PlaylistEntity> readAllPlaylists() {
        return playlistRepo.findAll();
    }

    public void update(PlaylistDto playlistDto, Long searchedPlaylistId) throws InvalidAlgorithmParameterException {
        Optional<PlaylistEntity> optionalPlaylistEntity = playlistRepo.findById(searchedPlaylistId);

        if (optionalPlaylistEntity.isPresent()) {
            PlaylistEntity existingPlaylistEntity = optionalPlaylistEntity.get();
            playlistRepo.save(existingPlaylistEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The playlist does not exist!");
        }
    }
}
