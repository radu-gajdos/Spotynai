package com.map.Domain.dto;
import com.map.Domain.entities.MembershipEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private MembershipEntity membershipEntity;

    private String userType;
}