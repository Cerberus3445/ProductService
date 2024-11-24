package com.cerberus.productservice.service.impl;

import com.cerberus.productservice.dto.ProductDto;
import com.cerberus.productservice.exception.ProductNotFountException;
import com.cerberus.productservice.mapper.EntityDtoMapper;
import com.cerberus.productservice.model.Product;
import com.cerberus.productservice.repository.ProductRepository;
import com.cerberus.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityDtoMapper mapper;


    @Override
    @Cacheable(value = "product", key = "#id")
    public ProductDto get(Long id) {
        return this.mapper.toDto(this.productRepository.findById(id)
                .orElseThrow());
    }

    @Override
    public List<ProductDto> getUserProducts(Long userId) {
        return this.mapper.toDto(this.productRepository.getProductsByUserId(userId)
                .orElseThrow());
    }

    @Override
    public void create(ProductDto productDto) {
        this.productRepository.save(this.mapper.toEntity(productDto));
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public void update(Long id, ProductDto productDto) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    this.productRepository.save(Product.builder()
                            .id(id)
                            .userId(productDto.getUserId())
                            .title(productDto.getTitle())
                            .description(productDto.getDescription())
                            .price(productDto.getPrice())
                            .category(productDto.getCategory())
                            .build());
                }, () -> {
                    throw new ProductNotFountException(id);
                });
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }
}
