package com.dmitriyabdurazakov.springboot.api.dto;

import com.dmitriyabdurazakov.springboot.data.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private String name;
    private CategoryStatus status;
    private List<ProductDTO> productsDto = new ArrayList<>();
}
