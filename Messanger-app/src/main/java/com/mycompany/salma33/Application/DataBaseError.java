package com.mycompany.salma33.Application;

public class DataBaseError extends Error {

    public DataBaseError(String errorMessage ) {
        super(errorMessage, "DB_ERROR","Some Thing Unexpected Happened " );
        
    }
   }

   

