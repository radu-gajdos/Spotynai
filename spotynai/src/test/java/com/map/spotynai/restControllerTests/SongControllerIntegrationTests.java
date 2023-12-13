package com.map.spotynai.restControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.Controllers.AlbumController;
import com.map.Domain.dto.AlbumDto;
import com.map.Domain.entities.AlbumEntity;
import com.map.Mappers.Mapper;
import com.map.Services.AlbumService;
import com.map.spotynai.TestConfig;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Import the additional configuration class
@Import(TestConfig.class)
@WebMvcTest(AlbumController.class)
class AlbumControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Mapper<AlbumEntity, AlbumDto> albumMapper;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deleteAlbum() throws Exception {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("mama mea e florareasa");


        ResultActions createResultActions = mockMvc.perform(post("/create_album")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(albumDto)))
                .andExpect(status().isOk());

        String createResponse = createResultActions.andReturn().getResponse().getContentAsString();
        Long albumId = objectMapper.readTree(createResponse).path("id").asLong();

        mockMvc.perform(delete("/delete_album/{id}", albumId))
                .andExpect(status().isNoContent());
    }

    @Test
    void createAndRetrieveAlbum() throws Exception {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Sample Album");
        albumDto.setId(11111L);
        albumDto.setReleaseDate(LocalDate.of(2020,11,11));
        albumDto.setArtistDto(null);

        ResultActions createResultActions = mockMvc.perform(post("/create_album")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(albumDto)))
                .andExpect(status().isOk());

        String createResponse = createResultActions.andReturn().getResponse().getContentAsString();
        Long albumId = objectMapper.readTree(createResponse).path("id").asLong();

        ResultActions resultActions1 = mockMvc.perform(get("/albums/{id}", albumId));
        resultActions1.andExpect(status().isOk());

        mockMvc.perform(delete("/delete_album/{id}", albumId))
                .andExpect(status().isNoContent());

        ResultActions resultActions = mockMvc.perform(get("/albums/{id}", albumId));

        resultActions.andExpect(status().isNotFound());
    }


    @Test
    void getAlbum_WithInvalidId() throws Exception {
        Long invalidAlbumId = 999L;
        ResultActions resultActions = mockMvc.perform(get("/albums/{id}", invalidAlbumId));

        resultActions.andExpect(status().isNotFound());
    }


}
