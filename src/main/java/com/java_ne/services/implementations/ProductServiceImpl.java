package com.java_ne.services.implementations;

import com.java_ne.exceptions.BadRequestException;
import com.java_ne.exceptions.CustomException;
import com.java_ne.dtos.product.CreateUpdateProduct;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.models.Product;
import com.java_ne.models.User;
import com.java_ne.repositories.IProductRepository;
import com.java_ne.services.interfaces.ProductService;
import com.java_ne.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final IProductRepository productRepository;
    private final UserService userService;
    @Override
    public ResponseEntity<ApiResponse<Product>> createProduct(CreateUpdateProduct productDTO) {
        try {
            User user = userService.getLoggedInUser();
            Product product = new Product();
            product.setCreatedBy(user);
            product.setDescription(productDTO.getDescription());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            return ApiResponse.success( "Product created successfully" , HttpStatus.OK  ,  productRepository.save(product));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> updateProduct(CreateUpdateProduct product, Long productId) {
        try {
            Product product1 = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
            if(!product1.getCreatedBy().getId().equals(userService.getLoggedInUser().getId())){
                throw new BadRequestException("You are not allowed to update this product");
            }
            product1.setDescription(product.getDescription());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            return ApiResponse.success( "Product updated successfully" , HttpStatus.OK, productRepository.save(product1));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> deleteProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("The product was not found"));
            if(!product.getCreatedBy().getId().equals(userService.getLoggedInUser().getId())){
                throw new BadRequestException("You are not allowed to delete this product");
            }
            productRepository.delete(product);
            return ApiResponse.success( "Product deleted successfully" , HttpStatus.OK, product);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> getProduct(Long productId) {
        try {
            return ApiResponse.success( "Product retrieved successfully" , HttpStatus.OK, productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found")));
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        try {
           return ApiResponse.success( "Products retrieved successfully" , HttpStatus.OK, productRepository.findAll());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByUser() {
        try {
            User user = userService.getLoggedInUser();
            return ApiResponse.success( "Products retrieved successfully" , HttpStatus.OK, productRepository.findAllByCreatedBy(user));
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
