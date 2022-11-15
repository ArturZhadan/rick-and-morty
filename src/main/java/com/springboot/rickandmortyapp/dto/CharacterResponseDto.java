package com.springboot.rickandmortyapp.dto;

import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.Status;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
