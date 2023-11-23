package com.map.Controllers;

import com.map.Domain.dto.UserDto;
import com.map.Domain.entities.UserEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.UserRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class UserController {
    private Mapper<UserEntity, UserDto> userMapper;

    private UserRepo userRepo;
    // private UserService userService;


    public UserController(Mapper<UserEntity, UserDto> userMapper, UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        // this.userService = userService;
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return userMapper.mapTo(savedUserEntity);
    }

    public UserDto deleteUser(UserDto userDto) throws InvalidAlgorithmParameterException {
        UserEntity userEntity = userMapper.mapFrom(userDto);

        Optional<UserEntity> optionalUserEntity = userRepo.findById(userEntity.getId());

        if (optionalUserEntity.isPresent()) {
            userRepo.delete(optionalUserEntity.get());
            return userMapper.mapTo(optionalUserEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The user does not exist!");
        }
    }

    public Iterable<UserEntity> readAllUsers() {
        return userRepo.findAll();
    }

    public void update(UserDto userDto, Long searchedUserId) throws InvalidAlgorithmParameterException {
        Optional<UserEntity> optionalUserEntity = userRepo.findById(searchedUserId);

        if (optionalUserEntity.isPresent()) {
            UserEntity existingUserEntity = optionalUserEntity.get();
            userRepo.save(existingUserEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The user does not exist!");
        }
    }
}
