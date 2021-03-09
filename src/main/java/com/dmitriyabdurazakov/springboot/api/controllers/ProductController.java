package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.api.PageRequestProvider;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.config.customEditors.StringToMapEditor;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api("Контроллер для работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PageRequestProvider requestProvider;

    @SneakyThrows
    @PostMapping(path = "/products", consumes = MediaType.ALL_VALUE)
    @Transactional
    @ApiOperation("Добавление продукта")
    public Product addProduct(@Valid @ModelAttribute ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.getCategories().clear();
        product.setImage(productDTO.getImage().getBytes());
        productService.saveProduct(product, productDTO.getCategoriesIds());
        return product;
    }

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getSortedProducts(@RequestBody Map<String, String> payload) {
        return productService.findAll(requestProvider.getPageRequest(Integer.parseInt(payload.get("pageNum")), Integer.parseInt(payload.get("elementsCount")), payload.get("sortBy")));
    }

    @GetMapping(path = "/products")
    public List<Product> getProductByName(@PathParam("name") String name) {
        return productService.findAllByName(name);
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