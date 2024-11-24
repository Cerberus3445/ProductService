package com.cerberus.productservice.controller;

import com.cerberus.productservice.dto.ProductDto;
import com.cerberus.productservice.exception.ProductValidationException;
import com.cerberus.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable("id") Long id){
        return this.productService.get(id);
    }

    @GetMapping("/user/{userId}")
    public List<ProductDto> geUserProducts(@PathVariable("userId") Long userId){
        return this.productService.getUserProducts(userId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ProductValidationException(collectErrorsToString(bindingResult.getFieldErrors()));
        }
        this.productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Товар создан");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,
                             @RequestBody @Valid ProductDto productDto,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ProductValidationException(collectErrorsToString(bindingResult.getFieldErrors()));
        }
        this.productService.update(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body("Товар обновлен");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        this.productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Товар удалён");
    }

    private String collectErrorsToString(List<FieldError> fieldErrors){
        return fieldErrors.stream().map(fieldError ->
                fieldError.getDefaultMessage()).toList().toString();
    }
}
