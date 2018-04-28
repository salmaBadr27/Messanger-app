package Database;

import Models.User;
import java.util.ArrayList;

abstract public class UserRepository {

    abstract public User getUserByUsername(String username);

    abstract public User addUser(String userName, String password);

    abstract public User removeUserByUserName(String username);
        
    abstract public ArrayList<User> getAllUsers ();

}
