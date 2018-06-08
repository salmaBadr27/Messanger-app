package Database;

import Models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class UsersMySQLRepository extends UserRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public UsersMySQLRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public User getUserByUsername(String username) {

        try {
            String query = "select*from user where User_Name = '" + username + "'";
            result = statement.executeQuery(query);
            while (result.next()) {
                String User_id = result.getString("user_id");
                String User_Name = result.getString("User_Name");
                String password = result.getString("password");
                String email = result.getString("E_mail");
                String mobile = result.getString("Mobile");
                User realUser = new User(User_id, User_Name, password, email, mobile);
                return realUser;

            }

        } catch (Exception ex) {
            System.out.print(ex);
        }
        return null;
    }

    @Override
    public User addUser(String userName, String password, String email, String phone) {

        try {
            String User_id = UUID.randomUUID().toString();
            statement.executeUpdate("INSERT INTO user (user_id, User_Name , password, E_mail, Mobile) VALUES ('"+ User_id +"' , '"+userName+"' , '"+password+"',  '"+ email +"' , '"+ phone+"')");
            User realUser = new User(User_id, userName, password, email, phone);
            return realUser;

        } catch (Exception e) {
            System.out.print(e);
        }

        return null;
    }

    @Override
    public User removeUserByUserName(String username) {
        try {

            String query = "select *from user where User_Name = '" + username + "'";
            result = statement.executeQuery(query);
            while (result.next()) {
                String userName = result.getString("User_Name");
                String pass = result.getString("password");
                User deletedUser = new User(userName, pass);
                if (username.equals(userName)) {
                    statement.executeUpdate("Delete from user where User_Name = '" + username + "'");
                    statement.executeUpdate("Delete from messages where sender = '" + username + "' or receiver = '" + username + "'");
                    return deletedUser;
                }
            }

        } catch (Exception ex) {

            System.out.print(ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList allUsers = new ArrayList();
        try {
            String query = "select * from user";
            result = statement.executeQuery(query);
            while (result.next()) {
                String userName = result.getString("User_Name");
                User user = new User(userName);
                allUsers.add(user);
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return allUsers;
    }
}
