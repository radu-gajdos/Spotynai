package Repositories;

import Domain.Membership;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepo extends CrudRepository<Membership, Long> {
}
