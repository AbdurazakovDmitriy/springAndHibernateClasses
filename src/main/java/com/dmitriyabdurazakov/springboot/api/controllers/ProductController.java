package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.api.dto.ProductRequestDto;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.config.customEditors.StringToMapEditor;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDtoFilter;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final ModelMapper modelMapper = new ModelMapper();

    @SneakyThrows
    @PostMapping(path = "/products", consumes = MediaType.ALL_VALUE)
    @Transactional
    @ApiOperation("Добавление продукта")
    public Product addProduct(@Valid @ModelAttribute ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @GetMapping(path = "/products")
    public Page<Product> getProductsByFilter(@ModelAttribute ProductRequestDto productRequestDto, Pageable pageable) {
        Page<Product> products = productService.findAllByFilter(modelMapper.map(productRequestDto, ProductDtoFilter.class), pageable);
        products.get().forEach(product -> {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class).getCategoriesByProductId(product.getId()))
                    .withRel("categories");
            product.add(selfLink);
        });
        return products;
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