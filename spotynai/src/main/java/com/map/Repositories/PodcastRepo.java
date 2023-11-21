package com.map.Repositories;
import com.map.Domain.entities.PodcastEntity;

import org.springframework.data.repository.CrudRepository;

public interface PodcastRepo  extends CrudRepository<PodcastEntity, Long> {
}
