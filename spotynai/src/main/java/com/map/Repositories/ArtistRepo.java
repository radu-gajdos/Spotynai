package com.map.Repositories;

import com.map.Domain.entities.ArtistEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepo  extends CrudRepository<ArtistEntity, Long> {
}
