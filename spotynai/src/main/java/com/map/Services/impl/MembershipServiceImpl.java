package com.map.Services.impl;

import com.map.Domain.entities.MembershipEntity;
import com.map.Repositories.MembershipRepo;
import com.map.Services.MembershipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepo membershipRepo;

    public MembershipServiceImpl(MembershipRepo membershipRepo) {
        this.membershipRepo = membershipRepo;
    }

    @Override
    public MembershipEntity createMembership(MembershipEntity membershipEntity) {
        return membershipRepo.save(membershipEntity);

    }

    @Override
    public List<MembershipEntity> findAll() {
        return StreamSupport.stream(membershipRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MembershipEntity> findOne(Long id) {
        return membershipRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return membershipRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        membershipRepo.deleteById(id);
    }
}
