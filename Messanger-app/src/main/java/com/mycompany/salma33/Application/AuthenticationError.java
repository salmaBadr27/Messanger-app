
package com.mycompany.salma33.Application;

public class AuthenticationError extends Error{
    
    public AuthenticationError(String errorMessage) {
        super(errorMessage, "BAD_JWT", "Something Unexpected Happened");
    }
    
}
