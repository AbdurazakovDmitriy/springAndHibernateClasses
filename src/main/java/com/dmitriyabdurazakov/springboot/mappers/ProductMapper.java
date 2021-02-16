package com.dmitriyabdurazakov.springboot.mappers;

import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.entity.dto.ProductDTO;
import lombok.SneakyThrows;
import org.aspectj.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    @SneakyThrows
    public Product mapProductDtoToProduct(ProductDTO dto) {
        byte[] image = FileUtil.readAsByteArray(new File(dto.getImagePath()));
        Product product = modelMapper.map(dto, Product.class);
        product.setImage(image);
        return product;
    }
}
