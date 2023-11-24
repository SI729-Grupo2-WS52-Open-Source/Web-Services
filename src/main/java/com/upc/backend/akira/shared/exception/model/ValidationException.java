package com.upc.backend.akira.shared.exception.model;

public class ValidationException extends RuntimeException{


    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
