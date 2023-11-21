package com.map.Mappers.impl;

import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;

public class SongMapperImpl implements Mapper<SongEntity, SongDto> {

    private final ModelMapper modelMapper;

    public SongMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SongDto mapTo(SongEntity songEntity) {
        return modelMapper.map(songEntity, SongDto.class);
    }

    @Override
    public SongEntity mapFrom(SongDto songDto) {
        return modelMapper.map(songDto, SongEntity.class);
    }

}
