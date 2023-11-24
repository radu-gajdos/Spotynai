package com.map.Services;

import com.map.Domain.entities.MembershipEntity;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    MembershipEntity createMembership(MembershipEntity membershipEntity);

    List<MembershipEntity> findAll();

    Optional<MembershipEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
