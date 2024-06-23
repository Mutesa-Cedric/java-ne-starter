package com.supamenu.www.services.interfaces;

import com.supamenu.www.dtos.product.CreateUpdateProduct;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.models.Product;
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
