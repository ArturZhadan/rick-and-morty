package com.springboot.rickandmortyapp.dto.external;

import lombok.Data;

@Data
public class ExternalResponseDto {
    private ExternalInfoDto info;
    private ExternalCharacterDto[] results;
}
