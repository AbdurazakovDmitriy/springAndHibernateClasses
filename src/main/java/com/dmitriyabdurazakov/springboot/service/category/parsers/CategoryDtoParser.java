package com.dmitriyabdurazakov.springboot.service.category.parsers;

import com.dmitriyabdurazakov.springboot.service.category.dto.CategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDtoParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CategoryDTO> getCategoryDTOList(List<String> paths) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        paths.forEach(path -> {
            try {
                categoryDTOS.add(objectMapper.readValue(new File(path), CategoryDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return categoryDTOS;
    }
}
