package com.map.Mappers.impl;

import com.map.Domain.dto.MembershipDto;
import com.map.Domain.entities.MembershipEntity;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper implements Mapper<MembershipEntity, MembershipDto> {
    private final ModelMapper modelMapper;

    public MembershipMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MembershipDto mapTo(MembershipEntity membershipEntity) {
        return modelMapper.map(membershipEntity, MembershipDto.class);
    }

    @Override
    public MembershipEntity mapFrom(MembershipDto membershipDto) {
        return modelMapper.map(membershipDto, MembershipEntity.class);
    }
}

