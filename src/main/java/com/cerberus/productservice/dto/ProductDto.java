package com.cerberus.productservice.dto;

import com.cerberus.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    private Long userId;

    private String title;

    private String description;

    private Long price;

    private Category category;
}
