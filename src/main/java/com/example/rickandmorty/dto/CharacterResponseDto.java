package com.example.rickandmorty.dto;

import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.Status;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
