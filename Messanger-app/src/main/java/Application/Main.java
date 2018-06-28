package Application;

import Database.*;
import Models.*;
import Utility.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import static spark.Spark.*;

public class Main {

    public static void main(String args[]) {
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

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
            try {
                String userinfo = req.body();
                User realUser = auth.AuthenticateWithJson(userMySQLRepository, userinfo);
                System.out.print(realUser);
                if (realUser == null) {
                    res.status(401);
                    return "not user ? sign up now";
                }
                String token = auth.generateToken(userinfo);
//                res.header("token", token);
//                res.header("Access-Control-Allow-Origin", "http://localhost:4567");
//                res.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//                res.header("Access-Control-Allow-Origin", "Content-Type, Authorization, Content-Length, X-Requested-With");
//                res.header("Access-Control-Allow-Credentials", "true");
                res.header("token", token);
                realUser.setToken(token);
                return gson.toJson(realUser);
            } catch (Exception ex) {
                return ex;
            }
        });
        // sign up 
        post("/Signup", (req, res) -> {
            String userinfo = req.body();
            User fakeUser = gson.fromJson(userinfo, User.class);
            User newUser = userMySQLRepository.addUser(fakeUser.getUserName(), fakeUser.getPassword(), fakeUser.getE_mail(), fakeUser.getMobileNum());
            if (newUser == null) {
                res.status(401);
                return " user name exist";
            }
            String token = auth.generateToken(userinfo);
            res.header("token", token);
                newUser.setToken(token);
                return gson.toJson(gson.toJson(newUser));
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
        post("/Users", (req, res) -> {
            try {
                //String token = req.headers("Authentication");
                String token = req.body();
                System.out.println("token : " + token);
                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
                System.out.println("isUser : " + isUser);
                if (isUser == null) {
                    return "unauthenticated";
                }
                ArrayList<User> allUsers = userMySQLRepository.getAllUsers();
                if (allUsers == null) {
                    return "there is no users";
                }
                return gson.toJson(allUsers);
            } catch (Exception e) {
                return e;
            }

        });
           post("/messages", (req, res) -> {
            String token = req.body();
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                res.status(401);
                return "unauthenticated";
            }
            String userName = isUser.getUserName();
            ArrayList<Message> allMessages = msgMYSQLRepository.getAllMessages(userName);
            System.out.print("messages"+gson.toJson(allMessages));
            return gson.toJson(allMessages);

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
            System.out.print("token"+token);
            User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
            if (isUser == null) {
                res.status(401);
                return "unauthenticated";
            }
            String userName = isUser.getUserName();
            ArrayList<Message> allMessages = msgMYSQLRepository.getAllMessages(userName);
            System.out.print("messages"+gson.toJson(allMessages));
            return gson.toJson(allMessages);

        });
       
    }

}
