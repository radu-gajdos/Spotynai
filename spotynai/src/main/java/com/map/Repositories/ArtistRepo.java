package com.map.Repositories;

import com.map.Domain.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepo  extends CrudRepository<Artist, Long> {
}
