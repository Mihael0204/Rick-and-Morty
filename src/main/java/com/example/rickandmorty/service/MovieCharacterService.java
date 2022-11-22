package com.example.rickandmorty.service;

import com.example.rickandmorty.model.MovieCharacter;
import java.util.List;

public interface MovieCharacterService {
    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String namepart);
}
