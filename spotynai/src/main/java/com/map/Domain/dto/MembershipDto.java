package com.map.Domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class MembershipDto {

    private Long id;

    private  int price;

    private LocalDate startDate;

    private LocalDate endDate;
}
