package com.map.Repositories;
import com.map.Domain.Podcast;

import org.springframework.data.repository.CrudRepository;

public interface PodcastRepo  extends CrudRepository<Podcast, Long> {
}
