package com.map.Repositories;

import com.map.Domain.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepo  extends CrudRepository<Album, Long> {
}
