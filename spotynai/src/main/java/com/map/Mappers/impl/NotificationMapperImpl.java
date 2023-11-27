package com.map.Mappers.impl;

import com.map.Domain.dto.NotificationDto;
import com.map.Domain.entities.Notification;
import com.map.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapperImpl implements Mapper<Notification, NotificationDto> {

    private final ModelMapper modelMapper;

    public NotificationMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificationDto mapTo(Notification notificationEntity) {
        return modelMapper.map(notificationEntity, NotificationDto.class);
    }

    @Override
    public Notification mapFrom(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }
}
