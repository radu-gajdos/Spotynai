package com.map.Mappers.impl;

import com.map.Domain.dto.GenreDto;
import com.map.Domain.entities.GenreEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapperImpl implements Mapper<GenreEntity, GenreDto> {
    private final ModelMapper modelMapper;

    public GenreMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDto mapTo(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public GenreEntity mapFrom(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }
}
