package com.dmitriyabdurazakov.springboot.entity.dto;

import com.dmitriyabdurazakov.springboot.entity.Category;
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
    private List<Category> categories = new ArrayList<>();
}
