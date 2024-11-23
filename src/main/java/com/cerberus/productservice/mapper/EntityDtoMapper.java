package com.cerberus.productservice.mapper;

import com.cerberus.productservice.dto.ProductDto;
import com.cerberus.productservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EntityDtoMapper {

    @Autowired
    private ModelMapper mapper;

    public Product toEntity(ProductDto productDto){
        return this.mapper.map(productDto, Product.class);
    }

    public ProductDto toDto(Product product){
        return this.mapper.map(product, ProductDto.class);
    }

    public List<ProductDto> toDto(List<Product> products){
        return products.stream().map(this::toDto).toList();
    }
}
