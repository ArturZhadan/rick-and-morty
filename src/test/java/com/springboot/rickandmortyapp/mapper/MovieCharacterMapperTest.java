package com.springboot.rickandmortyapp.mapper;

import com.springboot.rickandmortyapp.dto.CharacterResponseDto;
import com.springboot.rickandmortyapp.dto.external.ExternalCharacterDto;
import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieCharacterMapperTest {
    @InjectMocks
    private MovieCharacterMapper movieCharacterMapper;
    private MovieCharacter movieCharacter;
    private ExternalCharacterDto externalCharacterDto;

    @BeforeEach
    void setUp() {
        movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setGender(Gender.MALE);
        externalCharacterDto = new ExternalCharacterDto();
        externalCharacterDto.setId(1L);
        externalCharacterDto.setName("Rick Sanchez");
        externalCharacterDto.setStatus("Alive");
        externalCharacterDto.setGender("Male");
    }

    @Test
    public void toModel_Ok() {
        MovieCharacter actual = movieCharacterMapper.toModel(externalCharacterDto);
        Assertions.assertEquals(1L, actual.getExternalId());
        Assertions.assertEquals("Rick Sanchez", actual.getName());
        Assertions.assertEquals(Status.ALIVE, actual.getStatus());
        Assertions.assertEquals(Gender.MALE, actual.getGender());
    }

    @Test
    public void toDto_Ok() {
        CharacterResponseDto actual = movieCharacterMapper.toDto(movieCharacter);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(1L, actual.getExternalId());
        Assertions.assertEquals("Rick Sanchez", actual.getName());
        Assertions.assertEquals(Status.ALIVE, actual.getStatus());
        Assertions.assertEquals(Gender.MALE, actual.getGender());
    }
}