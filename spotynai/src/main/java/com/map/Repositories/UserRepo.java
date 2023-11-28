package com.map.Repositories;
import com.map.Domain.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
