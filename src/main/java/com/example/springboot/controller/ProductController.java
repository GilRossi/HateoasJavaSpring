package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repository.ProductRepository;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }
    
    @GetMapping("/products")
    public ResponseEntity<CollectionModel<EntityModel<ProductModel>>> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();
        
        List<EntityModel<ProductModel>> productModels = products.stream()
            .map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getOneProduct(product.getIdProduct())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products")))
            .collect(Collectors.toList());
        
        CollectionModel<EntityModel<ProductModel>> collectionModel = CollectionModel.of(productModels,
            linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        
        return ResponseEntity.ok(collectionModel);
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<EntityModel<ProductModel>> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        ProductModel product = productOptional.get();
        EntityModel<ProductModel> resource = EntityModel.of(product,
            linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
        
        return ResponseEntity.ok(resource);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<EntityModel<ProductModel>> updateProduct(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        ProductModel productModel = productOptional.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        ProductModel updatedProduct = productRepository.save(productModel);
        
        EntityModel<ProductModel> resource = EntityModel.of(updatedProduct,
            linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
        
        return ResponseEntity.ok(resource);
    }
    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") UUID id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}