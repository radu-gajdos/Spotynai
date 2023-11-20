package Repositories;

import Domain.Artist;
import Domain.Membership;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepo  extends CrudRepository<Artist, Long> {
}
