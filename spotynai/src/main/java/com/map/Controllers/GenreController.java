package com.map.Controllers;

import com.map.Domain.dto.GenreDto;
import com.map.Domain.dto.GenreDto;
import com.map.Domain.dto.GenreDto;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.GenreRepo;
import com.map.Services.GenreService;
import com.map.Services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class GenreController {
    private Mapper<GenreEntity, GenreDto> genreMapper;
    private GenreService genreService;

    public GenreController(Mapper<GenreEntity, GenreDto> genreMapper, GenreService genreService) {
        this.genreMapper = genreMapper;
        this.genreService = genreService;
    }

    @DeleteMapping(path = "/genres/{id}")
    public ResponseEntity deleteGenre(@PathVariable("id") Long id) {
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/genres")
    public List<GenreDto> listGenrees() {
        List<GenreEntity> genres = genreService.findAll();
        return genres.stream()
                .map(genreMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/genres/{id}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable("id") Long id) {
        Optional<GenreEntity> foundGenre = genreService.findOne(id);
        return foundGenre.map(genreEntity -> {
            GenreDto genreDto = genreMapper.mapTo(genreEntity);
            return new ResponseEntity<>(genreDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "genres/{id}")
    public ResponseEntity<GenreDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody GenreDto genreDto) {
        if (!genreService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        genreDto.setId(id);
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreService.createGenre(genreEntity);
        return new ResponseEntity<>(
                genreMapper.mapTo(savedGenreEntity), HttpStatus.OK);
    }
}
