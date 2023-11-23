package com.map.Domain.userFactory.userTypes;

import com.map.Domain.entities.UserEntity;
import com.map.Domain.userFactory.UserFactory;

import java.time.LocalDate;

class AdminUser implements UserFactory {
    @Override
    public UserEntity createUser(String username, String email, String password, LocalDate dateOfBirth) {
        UserEntity user = new UserEntity(null, username, email, password, dateOfBirth, null, null);
        user.setUserType("Admin");
        return user;
    }
}
