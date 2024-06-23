package com.supamenu.www.repositories;

import com.supamenu.www.models.Product;
import com.supamenu.www.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByCreatedBy(User user);
}
