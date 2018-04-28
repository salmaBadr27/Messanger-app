package Models;

public class User {

    //instances
    private String userId;
    private final String userName;
    private String password;

    //user constructor
    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public User(String UserName, String password) {
        this.password = password;
        this.userName = UserName;
    }

    public User(String userName) {
        this.userName = userName;
    }

    //getters and setters
    public String getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
    

}
