package com.example.rickandmorty.service;

import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.dto.external.ApiResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.model.Status;
import com.example.rickandmorty.repository.MovieCharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MovieCharacterServiceImplTest {
    @InjectMocks
    private MovieCharacterServiceImpl movieCharacterService;
    @Mock
    private MovieCharacterRepository repository;
    @Mock
    private MovieCharacterMapper movieCharacterMapper;

    @Test
    void saveDtosToDB_Ok() {
        ApiCharacterDto rickSanchez = new ApiCharacterDto();
        rickSanchez.setId(1L);
        rickSanchez.setName("Rick Sanchez");
        rickSanchez.setGender("MALE");
        rickSanchez.setStatus("ALIVE");
        ApiCharacterDto greebybobe = new ApiCharacterDto();
        greebybobe.setId(395L);
        greebybobe.setName("Greebybobe");
        greebybobe.setGender("UNKNOWN");
        greebybobe.setStatus("ALIVE");

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setInfo(null);
        apiResponseDto.setResults(new ApiCharacterDto[] {rickSanchez, greebybobe});

        Set<Long> externalIds = new HashSet<>();
        externalIds.add(rickSanchez.getId());
        externalIds.add(greebybobe.getId());
        MovieCharacter rickSanchezFromDb = new MovieCharacter();
        rickSanchezFromDb.setExternalId(rickSanchez.getId());
        rickSanchezFromDb.setName(rickSanchez.getName());
        rickSanchezFromDb.setGender(Gender.valueOf(rickSanchez.getGender()));
        rickSanchezFromDb.setStatus(Status.valueOf(rickSanchez.getStatus()));

        MovieCharacter greebybobeFromDb = new MovieCharacter();
        greebybobeFromDb.setExternalId(greebybobe.getId());
        greebybobeFromDb.setName(greebybobe.getName());
        greebybobeFromDb.setGender(Gender.valueOf(greebybobe.getGender()));
        greebybobeFromDb.setStatus(Status.valueOf(greebybobe.getStatus()));
        List<MovieCharacter> charactersToSave = List.of(rickSanchezFromDb, greebybobeFromDb);
        List<MovieCharacter> expected = List.of(rickSanchezFromDb);

        Mockito.when(repository.findAllByExternalIdIn(externalIds)).thenReturn(new ArrayList<>());
        Mockito.when(repository.saveAll(charactersToSave)).thenReturn(expected);
        Mockito.when(movieCharacterMapper.parseApiCharacterResponseDto(rickSanchez)).thenReturn(rickSanchezFromDb);
        Mockito.when(movieCharacterMapper.parseApiCharacterResponseDto(greebybobe)).thenReturn(greebybobeFromDb);

        List<MovieCharacter> actual = movieCharacterService.saveDtosToDB(apiResponseDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}