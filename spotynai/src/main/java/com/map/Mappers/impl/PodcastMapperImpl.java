package com.map.Mappers.impl;

import com.map.Domain.dto.PodcastDto;
import com.map.Domain.entities.PodcastEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;

public class PodcastMapperImpl implements Mapper<PodcastEntity, PodcastDto> {

    private final ModelMapper modelMapper;

    public PodcastMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PodcastDto mapTo(PodcastEntity podcastEntity) {
        return modelMapper.map(podcastEntity, PodcastDto.class);
    }

    @Override
    public PodcastEntity mapFrom(PodcastDto podcastDto) {
        return modelMapper.map(podcastDto, PodcastEntity.class);
    }

}