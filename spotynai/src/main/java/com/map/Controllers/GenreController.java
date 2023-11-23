package com.map.Controllers;

import com.map.Domain.dto.GenreDto;
import com.map.Domain.entities.GenreEntity;
import com.map.Mappers.Mapper;
import com.map.Repositories.GenreRepo;
import org.springframework.stereotype.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

@Controller
public class GenreController {
    private Mapper<GenreEntity, GenreDto> genreMapper;

    private GenreRepo genreRepo;
    // private GenreService genreService;


    public GenreController(Mapper<GenreEntity, GenreDto> genreMapper, GenreRepo genreRepo) {
        this.genreMapper = genreMapper;
        this.genreRepo = genreRepo;
        // this.genreService = genreService;
    }

    public GenreDto createGenre(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreRepo.save(genreEntity);
        return genreMapper.mapTo(savedGenreEntity);
    }

    public GenreDto deleteGenre(GenreDto genreDto) throws InvalidAlgorithmParameterException {
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);

        Optional<GenreEntity> optionalGenreEntity = genreRepo.findById(genreEntity.getId());

        if (optionalGenreEntity.isPresent()) {
            genreRepo.delete(optionalGenreEntity.get());
            return genreMapper.mapTo(optionalGenreEntity.get());
        } else {
            throw new InvalidAlgorithmParameterException("The genre does not exist!");
        }
    }

    public Iterable<GenreEntity> readAllGenres() {
        return genreRepo.findAll();
    }

    public void update(GenreDto genreDto, Long searchedGenreId) throws InvalidAlgorithmParameterException {
        Optional<GenreEntity> optionalGenreEntity = genreRepo.findById(searchedGenreId);

        if (optionalGenreEntity.isPresent()) {
            GenreEntity existingGenreEntity = optionalGenreEntity.get();
            genreRepo.save(existingGenreEntity);
        } else {
            throw new InvalidAlgorithmParameterException("The genre does not exist!");
        }
    }
}
