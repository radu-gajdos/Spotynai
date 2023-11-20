package Repositories;

import Domain.Membership;
import Domain.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo  extends CrudRepository<Playlist, Long> {
}
