package com.map.Repositories;

import com.map.Domain.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo  extends CrudRepository<GenreEntity, Long> {
}
