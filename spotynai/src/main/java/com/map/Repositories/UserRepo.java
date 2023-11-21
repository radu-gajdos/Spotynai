package com.map.Repositories;
import com.map.Domain.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

}
