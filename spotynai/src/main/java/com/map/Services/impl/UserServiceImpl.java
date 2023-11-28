package com.map.Services.impl;

import com.map.Domain.entities.PlaylistEntity;
import com.map.Domain.entities.UserEntity;
import com.map.Repositories.UserRepo;
import com.map.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepo.save(userEntity);

    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        UserEntity user = userRepo.findByUsernameAndPassword(username, password);
        return user != null;
    }
}