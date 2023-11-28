package com.map.Controllers;

import com.map.Domain.dto.GenreDto;
import com.map.Domain.dto.GenreDto;
import com.map.Domain.dto.GenreDto;
import com.map.Domain.dto.GenreDto;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Domain.entities.GenreEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.GenreRepo;
import com.map.Services.GenreService;
import com.map.Services.GenreService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class GenreController {
    @Autowired
    private Mapper<GenreEntity, GenreDto> genreMapper;
    @Autowired
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

    @PutMapping(path = "/update_genre/{id}")

    public ResponseEntity<GenreDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody GenreDto genreDto) {
        Optional<GenreEntity> existingGenreOptional = genreService.findOne(id);

        if (existingGenreOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GenreEntity existingGenre = existingGenreOptional.get();
        genreDto.setId(id);

        ObjectUpdater.updateFields(existingGenre, genreDto);

        GenreEntity savedGenreEntity = genreService.createGenre(existingGenre);

        return new ResponseEntity<>(
                genreMapper.mapTo(savedGenreEntity), HttpStatus.OK);
    }
}
