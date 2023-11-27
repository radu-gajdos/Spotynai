package com.map.teste;

import com.map.Domain.entities.ArtistEntity;
import com.map.Repositories.ArtistRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)public class Teste {

    @Autowired
    private ArtistRepo underTest;

    @Test
    public void testThatArtistCanBeCreatedAndRecalled() {
        ArtistEntity artistEntity = new ArtistEntity(null, "Guta", "Regele manelelor", null, null);
        underTest.save(artistEntity);

        Optional<ArtistEntity> result = underTest.findById(artistEntity.getId());

        assertThat(result).isPresent();
        ArtistEntity expectedArtist = result.get();
        expectedArtist.setSongEntities(null);
        assertThat(expectedArtist).isEqualTo(artistEntity);
    }

    @Test
    public void testThatMultipleArtistsCanBeCreatedAndRecalled() {
        ArtistEntity artistEntity = new ArtistEntity(null, "Guta", "Regele manelelor", null, null);
        underTest.save(artistEntity);

        ArtistEntity artistEntity1 = new ArtistEntity(null, "Salam", "Regele manelelor", null, null);
        underTest.save(artistEntity1);

        ArtistEntity artistEntity2 = new ArtistEntity(null, "Tzanca", "Regele manelelor", null, null);
        underTest.save(artistEntity2);

        Iterable<ArtistEntity> result = underTest.findAll();

        assertThat(result).hasSize(3).containsExactly(artistEntity, artistEntity1, artistEntity2);
    }
}
