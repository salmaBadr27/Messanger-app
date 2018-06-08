package Models;

public class User {

    //instances
    private String userId;
    private final String userName;
    private String password;
    private String e_mail;
    private String mobileNum;

    //user constructor
    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public User(String userId, String userName, String password, String e_mail, String mobileNum) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.e_mail = e_mail;
        this.mobileNum = mobileNum;
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

    public String getE_mail() {
        return e_mail;
    }

    public String getMobileNum() {
        return mobileNum;
    }
    public void setPassword(String Password) {
        this.password = Password;
    }
    

}
