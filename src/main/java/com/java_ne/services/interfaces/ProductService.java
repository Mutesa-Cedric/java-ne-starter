package com.java_ne.services.interfaces;

import com.java_ne.dtos.product.CreateUpdateProduct;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<ApiResponse<Product>> createProduct(CreateUpdateProduct product);
    public ResponseEntity<ApiResponse<Product>> updateProduct(CreateUpdateProduct product , Long productId);
    public ResponseEntity<ApiResponse<Product>> deleteProduct(Long productId);
    public ResponseEntity<ApiResponse<Product>> getProduct(Long productId);
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts();
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByUser();
}
