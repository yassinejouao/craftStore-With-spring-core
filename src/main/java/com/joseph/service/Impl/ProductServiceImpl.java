package com.joseph.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseph.entity.Product;
import com.joseph.service.ProductService;
import com.joseph.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Product with ID = ${id} is not found"));
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void saveProduct(Product Product) {
        productRepository.save(Product);

    }

    @Override
    public Product geProductsByName(String name) {
        return productRepository.findByNameproduct(name);
    }

}
