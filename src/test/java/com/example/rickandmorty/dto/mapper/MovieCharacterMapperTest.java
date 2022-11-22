package com.example.rickandmorty.dto.mapper;

import com.example.rickandmorty.dto.CharacterResponseDto;
import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MovieCharacterMapperTest {
    @InjectMocks
    private MovieCharacterMapper movieCharacterMapper;
    private MovieCharacter movieCharacter;
    private ApiCharacterDto apiCharacterDto;

    @BeforeEach
    void setUp() {
        movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setGender(Gender.MALE);
        apiCharacterDto = new ApiCharacterDto();
        apiCharacterDto.setId(1L);
        apiCharacterDto.setName("Rick Sanchez");
        apiCharacterDto.setGender("Male");
        apiCharacterDto.setStatus("Alive");
    }

    @Test
    void parseApiCharacterResponseDto_Ok() {
        MovieCharacter actual = movieCharacterMapper.parseApiCharacterResponseDto(apiCharacterDto);
        Assertions.assertEquals(1L, actual.getExternalId());
        Assertions.assertEquals("Rick Sanchez", actual.getName());
        Assertions.assertEquals(Status.ALIVE, actual.getStatus());
        Assertions.assertEquals(Gender.MALE, actual.getGender());

    }

    @Test
    void toResponseDto_Ok() {
        CharacterResponseDto actual = movieCharacterMapper.toResponseDto(movieCharacter);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(1L, actual.getExternalId());
        Assertions.assertEquals("Rick Sanchez", actual.getName());
        Assertions.assertEquals(Status.ALIVE, actual.getStatus());
        Assertions.assertEquals(Gender.MALE, actual.getGender());
    }
}