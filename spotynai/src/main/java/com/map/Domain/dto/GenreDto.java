package com.map.Domain.dto;

import com.map.Domain.entities.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GenreDto {

    private Long id;

    private String name;

    private String description;

    private List<SongDto> songDto;

}
