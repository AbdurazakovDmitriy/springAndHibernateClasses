package com.dmitriyabdurazakov.springboot.utils;

import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.entity.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.aspectj.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductParser {
    private final ModelMapper modelMapper = new ModelMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> parseProducts(List<String> paths) {
        List<Product> products = new ArrayList<>();
        paths.forEach(path -> {
            Product product = parseProduct(path);
            products.add(product);
        });
        return products;
    }

    @SneakyThrows
    public Product parseProduct(String path) {
        ProductDTO productDTO = objectMapper.readValue(new File(path), ProductDTO.class);
        return mapDtoToProduct(productDTO);
    }

    @SneakyThrows
    public Product mapDtoToProduct(ProductDTO dto) {
        byte[] image = FileUtil.readAsByteArray(new File(dto.getImagePath()));
        Product product = modelMapper.map(dto, Product.class);
        product.setImage(image);
        return product;
    }
}
