package com.springboot.rickandmortyapp.controller;

import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.model.Status;
import com.springboot.rickandmortyapp.service.MovieCharacterService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieCharacterControllerTest {
    @MockBean
    private MovieCharacterService movieCharacterService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void getRandomCharacter_Ok() {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setGender(Gender.MALE);
        Mockito.when(movieCharacterService.getRandomCharacter()).thenReturn(movieCharacter);
        RestAssuredMockMvc.when()
                .get("/movie-characters/random")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Rick Sanchez"))
                .body("externalId", Matchers.equalTo(1))
                .body("status", Matchers.equalTo("ALIVE"))
                .body("gender", Matchers.equalTo("MALE"));
    }

    @Test
    public void findAllByName_Ok() {
        String name = "Rick";
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setGender(Gender.MALE);
        List<MovieCharacter> list = List.of(movieCharacter);
        Mockito.when(movieCharacterService.findAllByNameContains(name)).thenReturn(list);
        RestAssuredMockMvc.given()
                .queryParam("name", name)
                .when()
                .get("/movie-characters/by-name")
                .then()
                .status(HttpStatus.OK)
                .body("size()", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Rick Sanchez"))
                .body("[0].status", Matchers.equalTo("ALIVE"))
                .body("[0].gender", Matchers.equalTo("MALE"));
    }
}