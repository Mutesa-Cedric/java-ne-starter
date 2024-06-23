package com.java_ne.repositories;

import com.java_ne.models.Product;
import com.java_ne.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByCreatedBy(User user);
}
