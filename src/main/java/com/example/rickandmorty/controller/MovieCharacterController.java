package com.example.rickandmorty.controller;

import com.example.rickandmorty.dto.CharacterResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService,
                                    MovieCharacterMapper mapper) {
        this.movieCharacterService = movieCharacterService;
        this.mapper = mapper;
    }

    @GetMapping("/random")
    @ApiOperation("Get random character from movie")
    public Object getRandom() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return mapper.toResponseDto(character);
    }

    @GetMapping("/by-name")
    @ApiOperation("Find all characters which names contains some namepart")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namepart) {
        return movieCharacterService.findAllByNameContains(namepart)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
