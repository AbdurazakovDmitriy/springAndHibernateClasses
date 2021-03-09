package com.dmitriyabdurazakov.springboot.service.dto;

import com.dmitriyabdurazakov.springboot.data.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    @Size(min = 5)
    private String name;
    private CategoryStatus status;
    private List<ProductDTO> productsDto = new ArrayList<>();
}
