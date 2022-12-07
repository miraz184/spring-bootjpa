package com.gltraining.springbootdemo.dao;

import com.gltraining.springbootdemo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IproductDao {
    void add(Product product);

    Optional<Product> findById(int id);

    Product update(Product product);

    List<Product> fetchAllProductsByPrice();

}
