package com.map.Domain.userFactory;

import com.map.Domain.entities.UserEntity;

import java.time.LocalDate;

public interface UserFactory {
    UserEntity createUser(String username, String email, String password, LocalDate dateOfBirth);
    //TODO cred ca asa e

}
