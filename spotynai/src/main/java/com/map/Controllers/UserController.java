package com.map.Controllers;

import com.map.Domain.dto.UserDto;
import com.map.Domain.dto.UserDto;
import com.map.Domain.dto.UserDto;
import com.map.Domain.entities.UserEntity;
import com.map.Domain.entities.UserEntity;
import com.map.Domain.entities.UserEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.UserRepo;
import com.map.Services.UserService;
import com.map.Services.UserService;
import com.map.config.ObjectUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class UserController {
    private Mapper<UserEntity, UserDto> userMapper;
    private UserService userService;

    public UserController(Mapper<UserEntity, UserDto> userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping(path = "/create_user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.createUser(userEntity);
        return userMapper.mapTo(savedUserEntity);
    }

    @DeleteMapping(path = "/delete_user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/users")
    public List<UserDto> listUseres() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_user/{id}")

    public ResponseEntity<UserDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto) {
        Optional<UserEntity> existingUserOptional = userService.findOne(id);

        if (existingUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity existingUser = existingUserOptional.get();
        userDto.setId(id);

        ObjectUpdater.updateFields(existingUser, userDto);

        UserEntity savedUserEntity = userService.createUser(existingUser);

        return new ResponseEntity<>(
                userMapper.mapTo(savedUserEntity), HttpStatus.OK);
    }


}