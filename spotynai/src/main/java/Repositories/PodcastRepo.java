package Repositories;
import Domain.Podcast;

import Domain.Membership;
import org.springframework.data.repository.CrudRepository;

public interface PodcastRepo  extends CrudRepository<Podcast, Long> {
}
