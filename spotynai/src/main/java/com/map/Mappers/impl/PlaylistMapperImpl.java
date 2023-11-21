package com.map.Mappers.impl;

import com.map.Domain.dto.PlaylistDto;
import com.map.Domain.entities.PlaylistEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMapperImpl implements Mapper<PlaylistEntity, PlaylistDto> {

    private final ModelMapper modelMapper;

    public PlaylistMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlaylistDto mapTo(PlaylistEntity playlistEntity) {
        return modelMapper.map(playlistEntity, PlaylistDto.class);
    }

    @Override
    public PlaylistEntity mapFrom(PlaylistDto playlistDto) {
        return modelMapper.map(playlistDto, PlaylistEntity.class);
    }

}
