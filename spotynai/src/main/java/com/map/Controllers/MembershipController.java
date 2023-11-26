package com.map.Controllers;

import com.map.Domain.dto.AlbumDto;
import com.map.Domain.dto.MembershipDto;
import com.map.Domain.dto.MembershipDto;
import com.map.Domain.dto.MembershipDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Domain.entities.MembershipEntity;
import com.map.Domain.entities.MembershipEntity;
import com.map.Domain.entities.MembershipEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.MembershipRepo;
import com.map.Services.MembershipService;
import com.map.Services.MembershipService;
import com.map.config.ObjectUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:5500")
public class MembershipController {
    private Mapper<MembershipEntity, MembershipDto> membershipMapper;
    private MembershipService membershipService;

    public MembershipController(Mapper<MembershipEntity, MembershipDto> membershipMapper, MembershipService membershipService) {
        this.membershipMapper = membershipMapper;
        this.membershipService = membershipService;
    }

    @PostMapping(path = "/memberships")
    public MembershipDto createMembership(@RequestBody MembershipDto membershipDto) {
        MembershipEntity membershipEntity = membershipMapper.mapFrom(membershipDto);
        MembershipEntity savedMembershipEntity = membershipService.createMembership(membershipEntity);
        return membershipMapper.mapTo(savedMembershipEntity);
    }

    @DeleteMapping(path = "/memberships/{id}")
    public ResponseEntity deleteMembership(@PathVariable("id") Long id) {
        membershipService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/memberships")
    public List<MembershipDto> listMembershipes() {
        List<MembershipEntity> memberships = membershipService.findAll();
        return memberships.stream()
                .map(membershipMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/memberships/{id}")
    public ResponseEntity<MembershipDto> getMembership(@PathVariable("id") Long id) {
        Optional<MembershipEntity> foundMembership = membershipService.findOne(id);
        return foundMembership.map(membershipEntity -> {
            MembershipDto membershipDto = membershipMapper.mapTo(membershipEntity);
            return new ResponseEntity<>(membershipDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_membership/{id}")

    public ResponseEntity<MembershipDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody MembershipDto membershipDto) {
        Optional<MembershipEntity> existingMembershipOptional = membershipService.findOne(id);

        if (existingMembershipOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MembershipEntity existingMembership = existingMembershipOptional.get();
        membershipDto.setId(id);

        // Use the ObjectUpdater utility
        ObjectUpdater.updateFields(existingMembership, membershipDto);

        MembershipEntity savedMembershipEntity = membershipService.createMembership(existingMembership);

        return new ResponseEntity<>(
                membershipMapper.mapTo(savedMembershipEntity), HttpStatus.OK);
    }
}
