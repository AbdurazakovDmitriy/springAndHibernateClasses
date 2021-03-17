package com.dmitriyabdurazakov.springboot.api.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductRequestDto {

    private Long id;

    private String name;

    private Long categoryId;
}
