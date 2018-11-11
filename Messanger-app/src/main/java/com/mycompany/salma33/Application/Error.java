package com.mycompany.salma33.Application;

public class Error extends Throwable {

    
    @Override
    public String toString() {
        return "{" + "\"ErrorMessage\": \"" + ErrorMessage + "\", \"ErrorCode\":\"" + ErrorCode + "\", \"ErrorDetails\":\"" + ErrorDetails + "\"}";
    }
     
    //instances 
    public String ErrorMessage;
    public String ErrorCode;
    public String ErrorDetails;
    
    
     //constructor
    public Error(String errorMessage, String errorCode,String errorDetails) {
        this.ErrorMessage = errorMessage;
        this.ErrorCode = errorCode;
        this.ErrorDetails = errorDetails;
    }
    //getters 
    @Override
    public String getMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getDetails() {
        return ErrorDetails;
    }
   
}
