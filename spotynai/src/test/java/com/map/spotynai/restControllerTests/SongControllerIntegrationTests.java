package com.map.spotynai.restControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.Controllers.SongController;
import com.map.Domain.dto.SongDto;
import com.map.Domain.entities.SongEntity;
import com.map.Mappers.Mapper;
import com.map.Services.SongService;
import com.map.spotynai.TestConfig;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Import the additional configuration class
@Import(TestConfig.class)
@WebMvcTest(SongController.class)
class SongControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Mapper<SongEntity, SongDto> songMapper;

    @MockBean
    private SongService songService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deleteSong() throws Exception {
        SongDto songDto = new SongDto();
        songDto.setTitle("mama mea e florareasa");


        ResultActions createResultActions = mockMvc.perform(post("/create_song")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk());

        String createResponse = createResultActions.andReturn().getResponse().getContentAsString();
        Long songId = objectMapper.readTree(createResponse).path("id").asLong();

        if (songId != null) {
            mockMvc.perform(delete("/delete_song/{id}", songId))
                    .andExpect(status().isNoContent());
        }
    }

//    @Test
//    void createAndRetrieveSong() throws Exception {
//        SongDto songDto = new SongDto();
//        songDto.setTitle("Sample Song");
//
//        ResultActions createResultActions = mockMvc.perform(post("/create_song")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(songDto)))
//                .andExpect(status().isOk());
//
//        String createResponse = createResultActions.andReturn().getResponse().getContentAsString();
//        Long songId = objectMapper.readTree(createResponse).path("id").asLong();
//
//        ResultActions resultActions1 = mockMvc.perform(get("/songs/{id}", songId));
//        resultActions1.andExpect(status().isOk());
//
//        mockMvc.perform(delete("/delete_song/{id}", songId))
//                .andExpect(status().isNoContent());
//
//        ResultActions resultActions = mockMvc.perform(get("/songs/{id}", songId));
//
//        resultActions.andExpect(status().isNotFound());
//    }


    @Test
    void getSong_WithInvalidId() throws Exception {
        Long invalidSongId = 999L;
        ResultActions resultActions = mockMvc.perform(get("/songs/{id}", invalidSongId));

        resultActions.andExpect(status().isNotFound());
    }


}
