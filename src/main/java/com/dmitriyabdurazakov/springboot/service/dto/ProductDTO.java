package com.dmitriyabdurazakov.springboot.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProductDTO {
    @Size(min = 3)
    private String name;
    private Map<String, String> characteristics;
    private String imagePath;
    private MultipartFile image;
    @Size(min = 1)
    private List<Long> categoriesIds = new ArrayList<>();
}
