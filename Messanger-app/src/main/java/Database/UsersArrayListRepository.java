package Database;

import Utility.Authentication;
import Models.User;
import java.util.ArrayList;
import java.util.UUID;

public class UsersArrayListRepository extends UserRepository {

    ArrayList<User> allUsers = new ArrayList<User>();

    public UsersArrayListRepository() {

        this.allUsers = new ArrayList<User>();
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User addUser(String userName, String password) {
        for (User user : allUsers) {
            if (user.getUserName().equals(userName)) {
                return null;
            }
        }
        String userid = UUID.randomUUID().toString();
        User newUser = new User(userid, userName, password);
        allUsers.add(newUser);
        return newUser;

    }


    @Override
    public User removeUserByUserName(String username) {
        for (User user : allUsers) {
            if (user.getUserName().equals(username)) {

                allUsers.remove(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
                return allUsers;
            }
        
    }


