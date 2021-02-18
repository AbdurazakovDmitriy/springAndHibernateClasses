package com.dmitriyabdurazakov.springboot.categories.entity;

import com.dmitriyabdurazakov.springboot.products.entity.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.categories.enums.CategoryStatus;
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
