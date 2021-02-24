package com.dmitriyabdurazakov.springboot.service.parsers;

import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ProductDTO> getProductDTOList(List<String> paths) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        paths.forEach(path -> {
            try {
                productDTOS.add(objectMapper.readValue(new File(path), ProductDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return productDTOS;
    }
}
