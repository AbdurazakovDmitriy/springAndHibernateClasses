package com.dmitriyabdurazakov.springboot.service.category.dto;

import com.dmitriyabdurazakov.springboot.data.enums.CategoryStatus;
import com.dmitriyabdurazakov.springboot.service.product.dto.ProductDTO;
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
