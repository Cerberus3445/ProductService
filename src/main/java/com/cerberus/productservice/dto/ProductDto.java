package com.cerberus.productservice.dto;

import com.cerberus.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private Long userId;

    private String title;

    private String description;

    private Long price;

    private Category category;
}
