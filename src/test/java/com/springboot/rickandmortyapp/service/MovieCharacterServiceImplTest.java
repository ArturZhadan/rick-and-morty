package com.springboot.rickandmortyapp.service;

import com.springboot.rickandmortyapp.dto.external.ExternalCharacterDto;
import com.springboot.rickandmortyapp.dto.external.ExternalInfoDto;
import com.springboot.rickandmortyapp.dto.external.ExternalResponseDto;
import com.springboot.rickandmortyapp.model.Gender;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.model.Status;
import com.springboot.rickandmortyapp.repository.MovieCharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class MovieCharacterServiceImplTest {
    @InjectMocks
    private MovieCharacterServiceImpl movieCharacterService;
    @Mock
    private MovieCharacterRepository movieCharacterRepository;
    private MovieCharacter movieCharacter;
    private ExternalResponseDto externalResponseDto;

    @BeforeEach
    void setUp() {
        movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setGender(Gender.MALE);
        ExternalInfoDto externalInfoDto = new ExternalInfoDto();
        externalInfoDto.setCount(1);
        externalInfoDto.setPages(1);
        externalInfoDto.setNext(null);
        externalInfoDto.setPrev(null);
        ExternalCharacterDto externalCharacterDto = new ExternalCharacterDto();
        externalCharacterDto.setId(1L);
        externalCharacterDto.setName("Rick Sanchez");
        externalCharacterDto.setStatus("Alive");
        externalCharacterDto.setGender("Male");
        externalResponseDto = new ExternalResponseDto();
        externalResponseDto.setInfo(externalInfoDto);
        externalResponseDto.setResults(new ExternalCharacterDto[]{externalCharacterDto});
    }

    @Test
    public void addCharacterForSave_Ok() {
        List<MovieCharacter> actual = movieCharacterService.saveDtosToDb(externalResponseDto);
        Mockito.when(movieCharacterRepository.findAllByExternalIdIn(Set.of(1L))).thenReturn(List.of(movieCharacter));
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Rick Sanchez", actual.get(0).getName());
    }
}