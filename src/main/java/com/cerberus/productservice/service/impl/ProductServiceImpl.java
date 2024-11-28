package com.cerberus.productservice.service.impl;

import com.cerberus.productservice.dto.ProductDto;
import com.cerberus.productservice.exception.ProductNotFountException;
import com.cerberus.productservice.mapper.EntityDtoMapper;
import com.cerberus.productservice.model.Product;
import com.cerberus.productservice.repository.ProductRepository;
import com.cerberus.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityDtoMapper mapper;


    @Override
    @Cacheable(value = "product", key = "#id")
    @Transactional(readOnly = true)
    public ProductDto get(Long id) {
        log.info("get {}", id);
        return this.mapper.toDto(this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFountException(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getUserProducts(Long userId) {
        log.info("getUserProducts {}", userId);
        return this.mapper.toDto(this.productRepository.getProductsByUserId(userId)
                .orElseThrow(ProductNotFountException::new));
    }

    @Override
    @Transactional
    public void create(ProductDto productDto) {
        log.info("create {}", productDto);
        this.productRepository.save(this.mapper.toEntity(productDto));
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    @Transactional
    public void update(Long id, ProductDto productDto) {
        log.info("update {}, {}", id, productDto);
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    this.productRepository.save(Product.builder()
                            .id(id)
                            .userId(productDto.getUserId())
                            .title(productDto.getTitle())
                            .description(productDto.getDescription())
                            .price(productDto.getPrice())
                            .category(productDto.getCategory())
                            .subcategory(productDto.getSubcategory())
                            .build());
                }, () -> {
                    throw new ProductNotFountException(id);
                });
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    @Transactional
    public void delete(Long id) {
        log.info("delete {}", id);
        this.productRepository.deleteById(id);
    }
}
