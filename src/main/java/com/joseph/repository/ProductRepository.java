package com.joseph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseph.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameproduct(String nameproduct);
}
