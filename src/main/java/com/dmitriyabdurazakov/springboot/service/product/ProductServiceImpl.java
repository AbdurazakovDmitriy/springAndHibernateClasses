package com.dmitriyabdurazakov.springboot.service.product;

import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.data.repository.ProductRepository;
import com.dmitriyabdurazakov.springboot.service.product.collectors.ProductsCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductsCollector productsCollector;

    @Override
    @Transactional
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths) {
        List<Product> productList = productsCollector.getProductList(productsFilesPaths);
        return productRepository.saveAll(productList);
    }
}
