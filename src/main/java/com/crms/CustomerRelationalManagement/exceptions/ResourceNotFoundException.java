package com.crms.CustomerRelationalManagement.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message ) ;
    }

    public ResourceNotFoundException(){
        super("something went wrong ") ;
    }

}
