package com.cerberus.productservice.dto;

import com.cerberus.productservice.model.Category;
import com.cerberus.productservice.model.Subcategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    @NotNull(message = "Id продавца не может быть пустым")
    private Long userId;

    @Length(min = 5, max = 200, message = "Минимальная длина названия составляет 5 символов, максимальная - 200 символов")
    @NotBlank(message = "Название не может быть пустым")
    private String title;

    @Length(max = 5000, message = "Максимальная длина описания составляет 5000 символов")
    @NotNull(message = "Описание не может быть пустым")
    private String description;

    @NotNull(message = "Цена не может быть пустой")
    @Positive(message = "Цена товара не может быть отрицательной")
    private Long price;

    @NotNull(message = "Категория товара не может быть пустой")
    private Category category;

    @NotNull(message = "Подкатегория товара не может быть пустой")
    private Subcategory subcategory;
}
