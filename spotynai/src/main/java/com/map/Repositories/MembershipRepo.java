package com.map.Repositories;

import com.map.Domain.entities.MembershipEntity;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepo extends CrudRepository<MembershipEntity, Long> {
}
