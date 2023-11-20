package Repositories;

import Domain.Album;
import Domain.Membership;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepo  extends CrudRepository<Album, Long> {
}
