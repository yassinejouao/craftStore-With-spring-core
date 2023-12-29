package com.joseph.service;

import java.util.List;

import com.joseph.entity.Product;

public interface ProductService {
    public List<Product> getProducts();

    public void saveProduct(Product Product);

    public Product getProduct(Long id);

    public void deleteProduct(Long id);

    public Product geProductsByName(String name);
}
