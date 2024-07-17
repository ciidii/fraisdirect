package com.fraisdirect.exception.custome;

public class EmailAlReadyExistException  extends RuntimeException{
    public EmailAlReadyExistException(String message){
        super(message);
    }
}
