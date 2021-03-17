package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.api.dto.ProductRequestDto;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.data.specifications.ProductSpecification;
import com.dmitriyabdurazakov.springboot.service.config.customEditors.StringToMapEditor;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api("Контроллер для работы с продуктами")
@Validated
public class ProductController {
    private final ProductService productService;

    @SneakyThrows
    @PostMapping(path = "/products", consumes = MediaType.ALL_VALUE)
    @Transactional
    @ApiOperation("Добавление продукта")
    public Product addProduct(@Valid @ModelAttribute ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @GetMapping(path = "/products")
    public Page<Product> getProductsByFilter(@ModelAttribute ProductRequestDto productRequestDto, Pageable pageable) {
        return productService.findAllByFilter(
                ProductSpecification.productNameLike(productRequestDto.getName())
                        .and(ProductSpecification.productIdEquals(productRequestDto.getId())
                                .and(ProductSpecification.productCategoriesHaveId(productRequestDto.getCategoryId()))), pageable);
    }

    @GetMapping(path = "/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.findById(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Map.class,
                new StringToMapEditor());
    }
}