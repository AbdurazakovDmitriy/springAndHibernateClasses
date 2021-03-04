package com.dmitriyabdurazakov.springboot.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private Map<String, String> characteristics;
    private String imagePath;
    private byte[] image;
    private List<Long> categoriesIds = new ArrayList<>();
}
