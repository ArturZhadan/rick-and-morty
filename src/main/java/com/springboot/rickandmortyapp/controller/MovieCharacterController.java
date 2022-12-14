package com.springboot.rickandmortyapp.controller;

import com.springboot.rickandmortyapp.dto.CharacterResponseDto;
import com.springboot.rickandmortyapp.mapper.MovieCharacterMapper;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.service.MovieCharacterService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper movieCharacterMapper;

    @GetMapping("/random")
    public CharacterResponseDto getRandom() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return movieCharacterMapper.toDto(character);
    }

    @GetMapping("/by-name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return movieCharacterService.findAllByNameContains(namePart).stream()
                .map(movieCharacterMapper::toDto)
                .collect(Collectors.toList());
    }
}
