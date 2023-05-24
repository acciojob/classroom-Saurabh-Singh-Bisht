package com.driver;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String ex){
        super(ex);
    }
}
