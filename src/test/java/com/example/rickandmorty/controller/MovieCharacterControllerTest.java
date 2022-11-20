package com.example.rickandmorty.controller;

import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.model.Status;
import com.example.rickandmorty.service.MovieCharacterService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieCharacterControllerTest {

    @MockBean
    private MovieCharacterService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void mustReturnRandomCharacter() {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setGender(Gender.valueOf("MALE"));
        movieCharacter.setStatus(Status.valueOf("ALIVE"));

        Mockito.when(service.getRandomCharacter()).thenReturn(movieCharacter);

        RestAssuredMockMvc.when()
                .get("/movie-characters/random")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Rick Sanchez"))
                .body("gender", Matchers.equalTo("MALE"))
                .body("status", Matchers.equalTo("ALIVE"));
    }

    @Test
    public void mustReturnCharactersWhichNamesContainsString() {
        MovieCharacter greebybobe = new MovieCharacter();
        greebybobe.setId(392L);
        greebybobe.setName("Greebybobe");
        greebybobe.setExternalId(395L);
        greebybobe.setGender(Gender.valueOf("UNKNOWN"));
        greebybobe.setStatus(Status.valueOf("ALIVE"));
        List<MovieCharacter> mockCharacters = List.of(greebybobe);

        Mockito.when(service.findAllByNameContains(any()))
                .thenReturn((List.of(greebybobe)));
        String namePart = "bob";

        RestAssuredMockMvc
                .given()
                .queryParam("name", namePart)
                .when()
                .get("/movie-characters/by-name")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(392))
                .body("[0].name", Matchers.equalTo("Greebybobe"))
                .body("[0].gender", Matchers.equalTo("UNKNOWN"))
                .body("[0].status", Matchers.equalTo("ALIVE"));
    }
}