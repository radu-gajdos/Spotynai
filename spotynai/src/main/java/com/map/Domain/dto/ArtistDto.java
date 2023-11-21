package com.map.Domain.dto;

import com.map.Domain.entities.SongEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long id;

    private String name;

    private String bio;

    private List<SongEntity> songEntities;
}
