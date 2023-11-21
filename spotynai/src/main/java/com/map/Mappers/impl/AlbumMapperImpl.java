package com.map.Mappers.impl;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapperImpl implements Mapper<AlbumEntity, AlbumDto> {

    private final ModelMapper modelMapper;

    public AlbumMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AlbumDto mapTo(AlbumEntity albumEntity) {
        return modelMapper.map(albumEntity, AlbumDto.class);
    }

    @Override
    public AlbumEntity mapFrom(AlbumDto albumDto) {
        return modelMapper.map(albumDto, AlbumEntity.class);
    }
}
