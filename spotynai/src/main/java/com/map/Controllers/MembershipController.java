package com.map.Controllers;

import com.map.Domain.dto.MembershipDto;
import com.map.Domain.entities.MembershipEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.MembershipRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class MembershipController {
    private Mapper<MembershipEntity, MembershipDto> membershipMapper;

    private MembershipRepo membershipRepo;
    // private MembershipService membershipService;


    public MembershipController(Mapper<MembershipEntity, MembershipDto> membershipMapper, MembershipRepo membershipRepo) {
        this.membershipMapper = membershipMapper;
        this.membershipRepo = membershipRepo;
        // this.membershipService = membershipService;
    }

    public MembershipDto createMembership(MembershipDto membershipDto) {
        MembershipEntity membershipEntity = membershipMapper.mapFrom(membershipDto);
        MembershipEntity savedMembershipEntity = membershipRepo.save(membershipEntity);
        return membershipMapper.mapTo(savedMembershipEntity);
    }

    public MembershipDto deleteMembership(MembershipDto membershipDto) throws InvalidAlgorithmParameterException {
        MembershipEntity membershipEntity = membershipMapper.mapFrom(membershipDto);

        Optional<MembershipEntity> optionalMembershipEntity = membershipRepo.findById(membershipEntity.getId());

        if (optionalMembershipEntity.isPresent()) {
            membershipRepo.delete(optionalMembershipEntity.get());
            return membershipMapper.mapTo(optionalMembershipEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The membership does not exist!");
        }
    }

    public Iterable<MembershipEntity> readAllMemberships() {
        return membershipRepo.findAll();
    }

    public void update(MembershipDto membershipDto, Long searchedMembershipId) throws InvalidAlgorithmParameterException {
        Optional<MembershipEntity> optionalMembershipEntity = membershipRepo.findById(searchedMembershipId);

        if (optionalMembershipEntity.isPresent()) {
            MembershipEntity existingMembershipEntity = optionalMembershipEntity.get();
            membershipRepo.save(existingMembershipEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The membership does not exist!");
        }
    }
}
