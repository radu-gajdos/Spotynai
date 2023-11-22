package com.map.Controllers;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.AlbumRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class AlbumController {
    private Mapper<AlbumEntity, AlbumDto> albumMapper;

    private AlbumRepo albumRepo;
    // private AlbumService albumService;


    public AlbumController(Mapper<AlbumEntity, AlbumDto> albumMapper, AlbumRepo albumRepo) {
        this.albumMapper = albumMapper;
        this.albumRepo = albumRepo;
        // this.albumService = albumService;
    }

    public AlbumDto createAlbum(AlbumDto albumDto) {
        AlbumEntity albumEntity = albumMapper.mapFrom(albumDto);
        AlbumEntity savedAlbumEntity = albumRepo.save(albumEntity);
        return albumMapper.mapTo(savedAlbumEntity);
    }

    public AlbumDto deleteAlbum(AlbumDto albumDto) throws InvalidAlgorithmParameterException {
        AlbumEntity albumEntity = albumMapper.mapFrom(albumDto);

        Optional<AlbumEntity> optionalAlbumEntity = albumRepo.findById(albumEntity.getId());

        if (optionalAlbumEntity.isPresent()) {
            albumRepo.delete(optionalAlbumEntity.get());
            return albumMapper.mapTo(optionalAlbumEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The album does not exist!");
        }
    }

    public Iterable<AlbumEntity> readAllAlbums() {
        return albumRepo.findAll();
    }

    public void update(AlbumDto albumDto, Long searchedAlbumId) throws InvalidAlgorithmParameterException {
        Optional<AlbumEntity> optionalAlbumEntity = albumRepo.findById(searchedAlbumId);

        if (optionalAlbumEntity.isPresent()) {
            AlbumEntity existingAlbumEntity = optionalAlbumEntity.get();
            albumRepo.save(existingAlbumEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The album does not exist!");
        }
    }




}

