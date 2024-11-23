package com.cerberus.productservice.service;

import com.cerberus.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto get(Long id);

    List<ProductDto> getUserProducts(Long userId);

    void create(ProductDto product);

    void update(Long id, ProductDto product);

    void delete(Long id);
}
