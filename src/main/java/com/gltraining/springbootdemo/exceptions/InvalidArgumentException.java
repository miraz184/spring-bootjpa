package com.gltraining.springbootdemo.exceptions;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String msg){
        super(msg);
    }
}
