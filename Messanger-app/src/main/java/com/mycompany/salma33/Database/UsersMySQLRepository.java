package com.mycompany.salma33.Database;

import com.mycompany.salma33.Models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import com.mycompany.salma33.Application.DataBaseError;
import com.google.gson.Gson;

public class UsersMySQLRepository extends UserRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;
    Gson gson = new Gson();

    public UsersMySQLRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public User getUserByUsername(String username) throws DataBaseError {
        String User_id = null, User_Name = null, password = null, email = null, mobile = null;
       // System.out.print("1");
        User realUser = new User(User_id, User_Name, password, email, mobile);
       // System.out.print("2");

        try {

            String query = "select*from user where User_Name = '" + username + "'";
            result = statement.executeQuery(query);
            if (result.next() == false) {
                System.out.print("throw");
                throw new DataBaseError("user name dosent exist");

            } else {
                do {
                   // System.out.print("do");

                    User_id = result.getString("user_id");
                    User_Name = result.getString("User_Name");
                    password = result.getString("password");
                    email = result.getString("E_mail");
                    mobile = result.getString("Mobile");
                   // System.out.print("user" + User_Name);
                    realUser = new User(User_id, User_Name, password, email, mobile);
                } while (result.next());
            }

        } catch (DataBaseError | SQLException ex ) {
             System.out.print(ex);
            throw new DataBaseError(ex.getMessage());
        }
        return realUser;
    }

    @Override
    public User addUser(String userName, String password, String email, String phone) throws DataBaseError {
        try {
            String User_id = UUID.randomUUID().toString();
            statement.executeUpdate("INSERT INTO user (user_id, User_Name , password, E_mail, Mobile) VALUES ('" + User_id + "' , '" + userName + "' , '" + password + "',  '" + email + "' , '" + phone + "')");
            User realUser = new User(User_id, userName, password, email, phone);
            return realUser;

        } catch (SQLException ex) {
            System.out.print(ex.getErrorCode() + ex.getMessage());
            throw new DataBaseError(ex.getMessage());
        }

    }

    @Override
    public User removeUserByUserName(String username) throws DataBaseError {
        String userName = null, pass = null;
        User deletedUser = new User(userName, pass);

        try {
            String query = "select *from user where User_Name = '" + username + "'";
            result = statement.executeQuery(query);
            if (result.next() == false) {

                throw new DataBaseError("user name not found");
            } else {
                do {
                    userName = result.getString("User_Name");
                    pass = result.getString("password");
                    deletedUser = new User(userName, pass);
                    if (username.equals(userName)) {
                        statement.executeUpdate("Delete from user where User_Name = '" + username + "'");
                        statement.executeUpdate("Delete from messages where sender = '" + username + "' or receiver = '" + username + "'");
                        return deletedUser;
                    }
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print(ex.getErrorCode() + ex.getMessage());
            throw new DataBaseError(ex.getMessage());
        }
        return deletedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() throws DataBaseError {
        ArrayList allUsers = new ArrayList();
        try {
            String query = "select * from user";
            result = statement.executeQuery(query);
            if (result.next() == false) {

                throw new DataBaseError("there is no users found");

            } else {

                do {
                    String userName = result.getString("User_Name");
                    User user = new User(userName);
                    allUsers.add(user);
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print(ex.getErrorCode());
            throw new DataBaseError(ex.getMessage());
        }
        return allUsers;
    }
}
