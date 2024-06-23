package com.supamenu.www.controllers;

import com.supamenu.www.dtos.product.CreateUpdateProduct;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.models.Product;
import com.supamenu.www.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
