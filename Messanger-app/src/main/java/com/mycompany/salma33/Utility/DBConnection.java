package com.mycompany.salma33.Utility;

import java.sql.*;

public class DBConnection {

    private Connection connection;
    private Statement statement;

    public DBConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            statement = connection.createStatement();

        } catch (Exception ex) {
            System.out.print("Error" + ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

}
