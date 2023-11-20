package Repositories;

import Domain.Genre;
import Domain.Membership;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo  extends CrudRepository<Genre, Long> {
}
