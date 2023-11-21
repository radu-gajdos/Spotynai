package com.map.Repositories;

import com.map.Domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo  extends CrudRepository<Genre, Long> {
}
