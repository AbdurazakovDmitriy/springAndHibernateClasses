package com.dmitriyabdurazakov.springboot.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDtoFilter {

    private Long id;

    private String name;

    private Long categoryId;
}
