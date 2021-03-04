package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import com.dmitriyabdurazakov.springboot.service.mappers.ProductMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api("Контроллер для работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping(path = "/products", consumes = MediaType.ALL_VALUE)
    @Transactional
    @ApiOperation("Добавление продукта")
    public ProductDTO addProduct(@ModelAttribute ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productService.saveProduct(product, productDTO.getCategoriesIds());
        return productDTO;
    }
}
