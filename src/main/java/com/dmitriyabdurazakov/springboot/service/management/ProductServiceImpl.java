package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.data.repository.CategoryRepository;
import com.dmitriyabdurazakov.springboot.data.repository.ProductRepository;
import com.dmitriyabdurazakov.springboot.service.broker.MessageBroker;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.mappers.ProductMapper;
import com.dmitriyabdurazakov.springboot.service.parsers.ProductDtoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MessageBroker messageBroker;
    private final ProductDtoParser productDtoParser;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        Product prod = productRepository.save(product);
        messageBroker.send("Продукт сохранен с id: " + prod.getId());
        return prod;
    }

    @Override
    @Transactional
    public List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths) {
        List<ProductDTO> productDTOList = productDtoParser.getProductDTOList(productsFilesPaths);
        Set<Category> allCategories = new HashSet<>();
        productDTOList.forEach(productDTO -> allCategories.addAll(categoryRepository.findAllById(productDTO.getCategoriesIds())));
        List<Product> productList = new ArrayList<>();
        productDTOList.forEach(dto -> {
            Product product = productMapper.mapProductDtoToProduct(dto);
            product.getCategories().clear();
            List<Category> categories = new ArrayList<>();
            dto.getCategoriesIds().forEach(id -> allCategories.forEach(category -> {
                if (category.getId().equals(id)) {
                    categories.add(category);
                }
            }));
            product.addCategories(categories);
            productList.add(product);
        });
        return productList;
    }
}
