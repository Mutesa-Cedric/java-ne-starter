package com.java_ne.controllers;

import com.java_ne.dtos.product.CreateUpdateProduct;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Product;
import com.java_ne.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody CreateUpdateProduct product) {
        return productService.createProduct(product);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody CreateUpdateProduct product, @PathVariable Long productId) {
        return productService.updateProduct(product, productId);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProduct( @PathVariable  Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByUser() {
        return productService.getProductsByUser();
    }
}
