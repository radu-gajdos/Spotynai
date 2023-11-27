package com.map.Controllers;

import com.map.Domain.dto.NotificationDto;
import com.map.Domain.entities.Notification;
import com.map.Mappers.Mapper;
import com.map.Services.NotificationService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5500")

public class NotificationController {
    @Autowired
    private Mapper<Notification, NotificationDto> notificationMapper;
    @Autowired
    private NotificationService notificationService;

    @PostMapping(path = "/create_notification")
    public NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {
        Notification notificationEntity = notificationMapper.mapFrom(notificationDto);
        Notification savedNotification = notificationService.createNotification(notificationEntity);
        return notificationMapper.mapTo(savedNotification);
    }

    @DeleteMapping(path = "/delete_notification/{id}")
    public ResponseEntity deleteNotification(@PathVariable("id") Long id) {
        notificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/notifications")
    public List<NotificationDto> listNotificationes() {
        List<Notification> notifications = notificationService.findAll();
        return notifications.stream()
                .map(notificationMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/notification/{id}")
    public ResponseEntity<NotificationDto> getNotification(@PathVariable("id") Long id) {
        Optional<Notification> foundNotification = notificationService.findOne(id);
        return foundNotification.map(notificationEntity -> {
            NotificationDto notificationDto = notificationMapper.mapTo(notificationEntity);
            return new ResponseEntity<>(notificationDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_notification/{id}")

    public ResponseEntity<NotificationDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody NotificationDto notificationDto) {
        Optional<Notification> existingNotificationOptional = notificationService.findOne(id);

        if (existingNotificationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Notification existingNotification = existingNotificationOptional.get();
        notificationDto.setId(id);

        ObjectUpdater.updateFields(existingNotification, notificationDto);

        Notification savedNotification = notificationService.createNotification(existingNotification);

        return new ResponseEntity<>(
                notificationMapper.mapTo(savedNotification), HttpStatus.OK);
    }

}

