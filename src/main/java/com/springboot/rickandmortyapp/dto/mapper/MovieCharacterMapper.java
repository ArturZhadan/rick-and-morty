package com.springboot.rickandmortyapp.dto.mapper;

import com.springboot.rickandmortyapp.dto.CharacterResponseDto;
import com.springboot.rickandmortyapp.dto.external.ApiCharacterDto;
import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponseDto(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public CharacterResponseDto toDto(MovieCharacter movieCharacter) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(movieCharacter.getId());
        dto.setExternalId(movieCharacter.getExternalId());
        dto.setName(movieCharacter.getName());
        dto.setStatus(movieCharacter.getStatus());
        dto.setGender(movieCharacter.getGender());
        return dto;
    }
}
