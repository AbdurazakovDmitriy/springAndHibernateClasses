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
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Product findById(String id) {
        return productRepository.findById(Long.parseLong(id)).orElse(null);
    }

    @SneakyThrows
    @Override
    @Transactional
    public Product saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        List<Category> categories = categoryRepository.findAllById(productDTO.getCategoriesIds());
        product.getCategories().clear();
        product.addCategories(categories);
        product.setImage(productDTO.getImage().getBytes());
        return product;
    }

    @Override
    public Page<Product> findAllByFilter(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
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
