package com.map.Services.impl;

import com.map.Domain.entities.Notification;
import com.map.Repositories.NotificationRepo;
import com.map.Services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;

    public NotificationServiceImpl(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepo.save(notification);

    }

    @Override
    public List<Notification> findAll() {
        return StreamSupport.stream(notificationRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Notification> findOne(Long id) {
        return notificationRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return notificationRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        notificationRepo.deleteById(id);
    }
}


