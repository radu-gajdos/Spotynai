package com.map.Repositories;

import com.map.Domain.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo  extends CrudRepository<Playlist, Long> {
}
