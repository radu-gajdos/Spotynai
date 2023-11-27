package com.map.Controllers;
;
import com.map.Domain.dto.ArtistDto;
import com.map.Domain.dto.ArtistDto;
import com.map.Domain.entities.ArtistEntity;
import com.map.Domain.entities.ArtistEntity;
import com.map.Mappers.Mapper;
import com.map.Services.ArtistService;
import com.map.config.ObjectUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class ArtistController {
    @Autowired
    private  Mapper<ArtistEntity, ArtistDto> artistMapper;
    @Autowired
    private  ArtistService artistService;


    @PostMapping(path = "/create_artist")
    public ArtistDto createArtist(@RequestBody ArtistDto artistDto) {
        ArtistEntity artistEntity = artistMapper.mapFrom(artistDto);
        ArtistEntity savedArtistEntity = artistService.createArtist(artistEntity);
        return artistMapper.mapTo(savedArtistEntity);
    }

    @DeleteMapping(path = "/delete_artist/{id}")
    public ResponseEntity deleteArtist(@PathVariable("id") Long id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/artists")
    public List<ArtistDto> listArtistes() {
        List<ArtistEntity> artists = artistService.findAll();
        return artists.stream()
                .map(artistMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/artists/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long id) {
        Optional<ArtistEntity> foundArtist = artistService.findOne(id);
        return foundArtist.map(artistEntity -> {
            ArtistDto artistDto = artistMapper.mapTo(artistEntity);
            return new ResponseEntity<>(artistDto, HttpStatus.OK);
        }).orElse (new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/update_artist/{id}")

    public ResponseEntity<ArtistDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody ArtistDto artistDto) {
        Optional<ArtistEntity> existingArtistOptional = artistService.findOne(id);

        if (existingArtistOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ArtistEntity existingArtist = existingArtistOptional.get();
        artistDto.setId(id);

        // Use the ObjectUpdater utility
        ObjectUpdater.updateFields(existingArtist, artistDto);

        ArtistEntity savedArtistEntity = artistService.createArtist(existingArtist);

        return new ResponseEntity<>(
                artistMapper.mapTo(savedArtistEntity), HttpStatus.OK);
    }
}
