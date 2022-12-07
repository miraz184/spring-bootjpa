package com.gltraining.springbootdemo.service;

import com.gltraining.springbootdemo.dao.IproductDao;
import com.gltraining.springbootdemo.entity.Product;
import com.gltraining.springbootdemo.exceptions.InvalidArgumentException;
import com.gltraining.springbootdemo.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{
    private IproductDao iproductDao;
    @Autowired
    public ProductServiceImpl(IproductDao dao){
        this.iproductDao = dao;
    }
    @Transactional
    @Override
    public Product addProduct(String name, double price) throws InvalidArgumentException {
        validateStringSizeRange(name,"name must of 2 to 15 characters");
        validateDoubleNotNegative(price,"Price can not be negative");
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        iproductDao.add(product);

        return product;
    }
    @Transactional(readOnly = true)
    @Override
    public Product findById(int id) throws InvalidArgumentException, ProductNotFoundException {
        validateIntegerNotLessThanOne(id, "Id must be greater than 0");
        Optional<Product> optional = iproductDao.findById(id);
        if(optional.isEmpty()){
            throw new ProductNotFoundException("Product id: "+id+" not found ");
        }
        return optional.get();
    }
    @Transactional
    @Override
    public Product changePrice(int id, double newPrice) throws InvalidArgumentException, ProductNotFoundException {
        validateIntegerNotLessThanOne(id, "Id must be greater than 0");
        validateDoubleNotNegative(newPrice,"Price must be positive");
        Product product = findById(id);
        if(product == null){
            throw new ProductNotFoundException("Product id: "+id+" not found ");
        }
        product.setPrice(newPrice);
        iproductDao.update(product);
        return product;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Product> fetchProductsOrderByPrice() {
        List<Product> productList = iproductDao.fetchAllProductsByPrice();
        return productList;
    }

    void validateStringSizeRange(String input, String msg) throws IllegalArgumentException{
        if(input.length() <2 || input.length()>15){
            input.toLowerCase().trim();
            throw new IllegalArgumentException(msg);
        }
    }

    void validateIntegerNotLessThanOne(Integer input, String msg) throws IllegalArgumentException{
        if(input<1){
            throw new IllegalArgumentException(msg);
        }
    }

    void validateDoubleNotNegative(double input, String msg) throws IllegalArgumentException{
        if(input<0){
            throw new IllegalArgumentException(msg);
        }
    }
}
