package com.map.Mappers.impl;

import com.map.Domain.dto.UserDto;
import com.map.Domain.entities.UserEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;

public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

}
