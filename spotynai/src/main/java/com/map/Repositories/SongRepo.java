package com.map.Repositories;
import com.map.Domain.entities.SongEntity;
import org.springframework.data.repository.CrudRepository;

public interface SongRepo extends CrudRepository<SongEntity, Long> {
}
