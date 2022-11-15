package com.springboot.rickandmortyapp.service;

import com.springboot.rickandmortyapp.model.MovieCharacter;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String namePart);
}
