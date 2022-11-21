package com.springboot.rickandmortyapp.dto.external;

import lombok.Data;

@Data
public class ExternalInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
