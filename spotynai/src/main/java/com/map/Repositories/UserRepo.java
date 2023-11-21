package com.map.Repositories;
import com.map.Domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

}
