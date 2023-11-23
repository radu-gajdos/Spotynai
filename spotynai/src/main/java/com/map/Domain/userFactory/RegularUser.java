package com.map.Domain.userFactory;

import com.map.Domain.entities.UserEntity;

import java.time.LocalDate;

class RegularUserFactory implements UserFactory {
    @Override
    public UserEntity createUser(String username, String email, String password, LocalDate dateOfBirth) {
       // UserEntity user = new UserEntity(username, email, password, dateOfBirth);
        // de ferificat cum ii constructorul
        user.setUserType("User");
        return user;
    }

}

