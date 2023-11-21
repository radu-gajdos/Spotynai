package com.map.Controllers;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.AlbumRepo;
import com.map.Services.AlbumService;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AlbumController {
    private Mapper<AlbumEntity, AlbumDto> albumMapper;
    private AlbumService albumService;


    public void Controller( Mapper<AlbumEntity, AlbumDto> albumMapper,  AlbumService albumService) {
        this.albumMapper = albumMapper;
        this.albumService = albumService;
    }

    public AlbumDto createAlbum(AlbumDto albumDto) {
        AlbumEntity albumEntity = albumMapper.mapFrom(albumDto);
        AlbumEntity savedAlbumEntity = albumService.createAlbum(albumEntity);
        return albumMapper.mapTo(savedAlbumEntity);
    }


}

