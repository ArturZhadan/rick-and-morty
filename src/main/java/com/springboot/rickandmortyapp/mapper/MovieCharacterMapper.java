package com.springboot.rickandmortyapp.mapper;

import com.springboot.rickandmortyapp.dto.CharacterResponseDto;
import com.springboot.rickandmortyapp.dto.external.ExternalCharacterDto;
import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter toModel(ExternalCharacterDto dto) {
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

    public MovieCharacter updateCharacter(MovieCharacter existing, ExternalCharacterDto dto) {
        existing.setName(dto.getName());
        existing.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        existing.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        return existing;
    }
}
