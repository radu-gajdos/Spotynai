package com.map.Controllers;

import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.SongRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class SongController {
    private Mapper<SongEntity, SongDto> songMapper;

    private SongRepo songRepo;
    // private SongService songService;


    public SongController(Mapper<SongEntity, SongDto> songMapper, SongRepo songRepo) {
        this.songMapper = songMapper;
        this.songRepo = songRepo;
        // this.songService = songService;
    }

    public SongDto createSong(SongDto songDto) {
        SongEntity songEntity = songMapper.mapFrom(songDto);
        SongEntity savedSongEntity = songRepo.save(songEntity);
        return songMapper.mapTo(savedSongEntity);
    }

    public SongDto deleteSong(SongDto songDto) throws InvalidAlgorithmParameterException {
        SongEntity songEntity = songMapper.mapFrom(songDto);

        Optional<SongEntity> optionalSongEntity = songRepo.findById(songEntity.getId());

        if (optionalSongEntity.isPresent()) {
            songRepo.delete(optionalSongEntity.get());
            return songMapper.mapTo(optionalSongEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The song does not exist!");
        }
    }

    public Iterable<SongEntity> readAllSongs() {
        return songRepo.findAll();
    }

    public void update(SongDto songDto, Long searchedSongId) throws InvalidAlgorithmParameterException {
        Optional<SongEntity> optionalSongEntity = songRepo.findById(searchedSongId);

        if (optionalSongEntity.isPresent()) {
            SongEntity existingSongEntity = optionalSongEntity.get();
            songRepo.save(existingSongEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The song does not exist!");
        }
    }
}
