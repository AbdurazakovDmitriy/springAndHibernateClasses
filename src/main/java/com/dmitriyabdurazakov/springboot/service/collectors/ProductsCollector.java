package com.dmitriyabdurazakov.springboot.service.collectors;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.service.management.CategoryService;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.mappers.ProductMapper;
import com.dmitriyabdurazakov.springboot.service.parsers.ProductDtoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsCollector {
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final ProductDtoParser productDtoParser;

    @Transactional(value = Transactional.TxType.MANDATORY)
    public List<Product> getProductList(List<String> paths) {
        List<ProductDTO> productDTOList = productDtoParser.getProductDTOList(paths);
        List<Category> allCategories = categoryService.findAll();
        List<Product> productList = new ArrayList<>();
        productDTOList.forEach(dto -> {
            Product product = productMapper.mapProductDtoToProduct(dto);
            product.getCategories().clear();
            List<Category> categories = new ArrayList<>();
            dto.getCategoriesIds().forEach(id -> {
                allCategories.forEach(category -> {
                    if (category.getId().equals(id)) {
                        categories.add(category);
                    }
                });
            });
            if (categories.size() < dto.getCategoriesIds().size()) {
                throw new RuntimeException("Не найдена одна или несколько категорий с ID " + dto.getCategoriesIds());
            }
            product.addCategories(categories);
            productList.add(product);
        });
        return productList;
    }
}
