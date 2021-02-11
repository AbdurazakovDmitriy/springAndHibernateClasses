package com.dmitriyabdurazakov.springboot.entity.dto;

import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.enums.CategoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private String name;
    private CategoryStatus status;
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
