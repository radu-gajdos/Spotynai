package com.map.Mappers.impl;

import com.map.Domain.dto.ArtistDto;
import com.map.Domain.entities.ArtistEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapperImpl implements Mapper<ArtistEntity, ArtistDto> {

    private final ModelMapper modelMapper;

    public ArtistMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ArtistDto mapTo(ArtistEntity artistEntity) {
        return modelMapper.map(artistEntity, ArtistDto.class);
    }

    @Override
    public ArtistEntity mapFrom(ArtistDto artistDto) {
        return modelMapper.map(artistDto, ArtistEntity.class);
    }
}
