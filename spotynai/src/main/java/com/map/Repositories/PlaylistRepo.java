package com.map.Repositories;

import com.map.Domain.entities.PlaylistEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo  extends CrudRepository<PlaylistEntity, Long> {
}
