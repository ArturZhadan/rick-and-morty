package com.springboot.rickandmortyapp.service;

import com.springboot.rickandmortyapp.dto.external.ExternalCharacterDto;
import com.springboot.rickandmortyapp.dto.external.ExternalResponseDto;
import com.springboot.rickandmortyapp.mapper.MovieCharacterMapper;
import com.springboot.rickandmortyapp.model.MovieCharacter;
import com.springboot.rickandmortyapp.repository.MovieCharacterRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper movieCharacterMapper;

    @PostConstruct
    @Scheduled(cron = "0 8 * * * ?")
    @Override
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters method was invoked at " + LocalDateTime.now());
        ExternalResponseDto externalResponseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ExternalResponseDto.class);
        saveDtosToDb(externalResponseDto);
        while (externalResponseDto.getInfo().getNext() != null) {
            externalResponseDto = httpClient.get(externalResponseDto.getInfo().getNext(),
                    ExternalResponseDto.class);
            saveDtosToDb(externalResponseDto);
        }
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);
        return movieCharacterRepository.findById(randomId).orElseThrow(
                () -> new RuntimeException("Can`t find random character by id"));
    }

    @Override
    public List<MovieCharacter> findAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart);
    }

    public List<MovieCharacter> saveDtosToDb(ExternalResponseDto externalResponseDto) {
        Map<Long, ExternalCharacterDto> externalDtos =
                Arrays.stream(externalResponseDto.getResults())
                .collect(Collectors.toMap(ExternalCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();
        List<MovieCharacter> existingCharacters =
                movieCharacterRepository.findAllByExternalIdIn(externalIds);
        Map<Long, MovieCharacter> existingCharacterWithIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharacterWithIds.keySet();
        externalIds.retainAll(existingIds);
        List<MovieCharacter> movieCharactersToUpdate = existingCharacters.stream()
                .map(i -> movieCharacterMapper.updateCharacter(i,
                        externalDtos.get(i.getExternalId())))
                .collect(Collectors.toList());
        existingIds = existingCharacterWithIds.keySet();
        externalIds.removeAll(existingIds);
        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(i -> movieCharacterMapper.toModel(externalDtos.get(i)))
                .collect(Collectors.toList());
        charactersToSave.addAll(movieCharactersToUpdate);
        return movieCharacterRepository.saveAll(charactersToSave);
    }
}
