package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.data.repository.ProductRepository;
import com.dmitriyabdurazakov.springboot.service.broker.MessageBroker;
import com.dmitriyabdurazakov.springboot.service.collectors.ProductsCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductsCollector productsCollector;
    private final MessageBroker messageBroker;

    @Override
    @Transactional
    public List<Product> saveAll(List<Product> products) {
        List<Product> productList = productRepository.saveAll(products);
        List<Long> productsIds = productList.stream().map(Product::getId).collect(Collectors.toList());
        messageBroker.send("Продукты сохранены с id: " + productsIds);
        return productList;
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
        List<Long> productsIds = productList.stream().map(Product::getId).collect(Collectors.toList());
        messageBroker.send("Продукты сохранены с id: " + productsIds);
        return productRepository.saveAll(productList);
    }
}
