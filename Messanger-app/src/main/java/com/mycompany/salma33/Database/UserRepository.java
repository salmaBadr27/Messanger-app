package com.mycompany.salma33.Database;

import com.mycompany.salma33.Application.DataBaseError;
import com.mycompany.salma33.Models.User;

import java.util.ArrayList;

abstract public class UserRepository {

    abstract public User getUserByUsername(String username)throws DataBaseError ;

    abstract public User addUser(String userName, String password,String email , String phone) throws DataBaseError;

    abstract public User removeUserByUserName(String username)throws DataBaseError;
        
    abstract public ArrayList<User> getAllUsers ()throws DataBaseError;

}
