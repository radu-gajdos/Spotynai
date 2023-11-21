package com.map.Repositories;

import com.map.Domain.entities.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepo  extends CrudRepository<AlbumEntity, Long> {
}
