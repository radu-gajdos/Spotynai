package com.map.Services.impl;

import com.map.Domain.entities.Notification;
import com.map.Domain.entities.SongEntity;
import com.map.Domain.mementos.SongMemento;
import com.map.Repositories.NotificationRepo;
import com.map.Repositories.SongRepo;
import com.map.Services.SongService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
@NoArgsConstructor
public class SongServiceImpl implements SongService {

    @Autowired
    private  SongRepo songRepo;
    @Autowired
    private  NotificationRepo notificationRepo;



    @Override
    public SongEntity createSong(SongEntity songEntity) {
        String a =  songEntity.getTitle() + "was added";
        Notification notification = new Notification(null,"New song added ",a, LocalDate.now());
        notificationRepo.save(notification);
        return songRepo.save(songEntity);

    }

    @Override
    public List<SongEntity> findAll() {
        return StreamSupport.stream(songRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SongEntity> findOne(Long id) {
        return songRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return songRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        songRepo.deleteById(id);
    }
}

