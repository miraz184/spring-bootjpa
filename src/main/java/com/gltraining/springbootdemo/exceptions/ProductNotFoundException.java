package com.gltraining.springbootdemo.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String msg){
        super(msg);
    }
}
