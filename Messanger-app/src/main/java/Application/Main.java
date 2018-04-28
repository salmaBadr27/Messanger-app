package Application;

import Database.*;
import Models.*;
import Utility.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import static spark.Spark.*;

public class Main {

    public static void main(String args[]) {

        Gson gson = new Gson();
        DBConnection dbConnection = new DBConnection();
        Authentication auth = new Authentication("secret");
        UserRepository userMySQLRepository = new UsersMySQLRepository(dbConnection.getConnection(), dbConnection.getStatement());
        MessageRepository msgMYSQLRepository = new MessagesMySQLRepository(dbConnection.getConnection(), dbConnection.getStatement());

        //-------------------------
        //*apis using DataBase Repo*//
        //-------------------------
        //login 
        post("/login", (req, res) -> {
            String userinfo = req.body();
            User realUser = auth.AuthenticateWithJson(userMySQLRepository, userinfo);
            if (realUser == null) {
                return "not user ? sign up now";
            }
            String token = auth.generateToken(userinfo);
            res.header("token", token);
            return gson.toJson(realUser);

        });
        // sign up 
        post("/Signup", (req, res) -> {
            String userinfo = req.body();
            User fakeUser = gson.fromJson(userinfo, User.class);
            User newUser = userMySQLRepository.addUser(fakeUser.getUserName(), fakeUser.getPassword());
            if (newUser == null) {
                return " user name exist";
            }
            String token = auth.generateToken(userinfo);
            res.header("token", token);
            return gson.toJson(newUser);
        });

//        delete user by user name 
        delete("/User/:username", (req, res) -> {
            String username = req.params("username");
            String token = req.headers("Authentication");
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                return " unauthenticated";
            }
            if (isUser.getUserName().equals(username)) {
                User DeletedUser = userMySQLRepository.removeUserByUserName(username);
                if (DeletedUser == null) {
                    return " not found";
                }
                return gson.toJson(DeletedUser);
            }
            return "unauthorized";
        });

        //get allusers
        get("/Users", (req, res) -> {
            String token = req.headers("Authentication");
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                return "unauthenticated";
            }
            ArrayList<User> allUsers = userMySQLRepository.getAllUsers();
            if (allUsers == null) {
                return "there is no users";
            }
            return gson.toJson(allUsers);

        });

//        //create new msg 
        post("/msg/:receiver", (req, res) -> {
            String token = req.headers("Authentication");
            String msgBody = req.body();
            String receiver = req.params("receiver");
            User isuser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isuser == null) {
                res.status(401);
                return "unauthenticated";
            }
            User validReceiver = userMySQLRepository.getUserByUsername(receiver);
            if (validReceiver == null) {
                return "cannot find this user";
            }
            if (receiver.equals(isuser.getUserName())) {

                return " cannot send msg to yourself , enta mgnon ? ";

            }
            Message newMessage = msgMYSQLRepository.sendMessages(msgBody, isuser.getUserName(), receiver);
            return gson.toJson(newMessage);
        });
//
//        //get messages by sender 
        get("Msgs/:username", (req, res) -> {
            String token = req.headers("Authentication");
            String isSender = req.params("username");
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                res.status(401);
                return "unauthenticated";
            }
            if (isSender.equals(isUser.getUserName())) {

                ArrayList<Message> sendedMessages = msgMYSQLRepository.getMessageBySender(isSender);
                return gson.toJson(sendedMessages);
            }
            return "unauthorized";

        });
//
        //remove messages by sender 
        delete("messages/:sender", (req, res) -> {
            String token = req.headers("Authentication");
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            String sender = req.params("sender");
            if (isUser == null) {
                res.status(401);
                return "unauthenticated";
            }
            if (sender.equals(isUser.getUserName())) {

                ArrayList<Message> deletedMsgs = msgMYSQLRepository.removeMessageBySender(sender);
                return gson.toJson(deletedMsgs);
            }
            res.status(401);
            return "unauthorized";

        });
        //get all messages by user name
        get("/Messages", (req, res) -> {
            String token = req.headers("Authentication");
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                res.status(401);
                return "unauthenticated";
            }
            String userName = isUser.getUserName();
            ArrayList<Message> allMessages = msgMYSQLRepository.getAllMessages(userName);
            return gson.toJson(allMessages);

        });
    }

}
