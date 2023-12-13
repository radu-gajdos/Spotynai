package com.map.Domain.userFactory.userTypes;

import com.map.Domain.entities.UserEntity;
import com.map.Domain.userFactory.UserFactory;

import java.time.LocalDate;

class RegularUser implements UserFactory {
    @Override
    public UserEntity createUser(String username, String email, String password, LocalDate dateOfBirth) {
        UserEntity user = new UserEntity(null, username, email, password, dateOfBirth, null, null, null);
        user.setUserType("User");
        return user;
    }

}

