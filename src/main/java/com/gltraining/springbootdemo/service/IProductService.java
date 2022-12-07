package com.gltraining.springbootdemo.service;

import com.gltraining.springbootdemo.entity.Product;
import com.gltraining.springbootdemo.exceptions.InvalidArgumentException;
import com.gltraining.springbootdemo.exceptions.ProductNotFoundException;

import java.util.List;


public interface IProductService {

    Product addProduct(String name, double price) throws InvalidArgumentException;

    Product findById(int id) throws InvalidArgumentException, ProductNotFoundException;

    Product changePrice(int id, double newPrice) throws InvalidArgumentException, ProductNotFoundException;

    List<Product> fetchProductsOrderByPrice();
}
