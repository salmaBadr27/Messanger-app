package com.mycompany.salma33;

//import com.mycompany.salma33.Models.User;
//import com.mycompany.salma33.Models.Message;
//import com.mycompany.salma33.Utility.Authentication;
//import com.mycompany.salma33.Utility.DBConnection;
//import com.mycompany.salma33.Database.MessageRepository;
//import com.mycompany.salma33.Database.UsersMySQLRepository;
//import com.mycompany.salma33.Database.MessagesMySQLRepository;
//import com.mycompany.salma33.Database.UserRepository;
//import com.google.gson.Gson;
//import com.mycompany.salma33.Application.AuthenticationError;
//import com.mycompany.salma33.Application.DataBaseError;
//import com.mycompany.salma33.Database.UsersArrayListRepository;
//import java.util.ArrayList;
import static spark.Spark.*;

public class Main {

    public static void main(String args[]) {
//        options("/*",
//                (request, response) -> {
//
//                    String accessControlRequestHeaders = request
//                            .headers("Access-Control-Request-Headers");
//                    if (accessControlRequestHeaders != null) {
//                        response.header("Access-Control-Allow-Headers",
//                                accessControlRequestHeaders);
//                    }
//
//                    String accessControlRequestMethod = request
//                            .headers("Access-Control-Request-Method");
//                    if (accessControlRequestMethod != null) {
//                        response.header("Access-Control-Allow-Methods",
//                                accessControlRequestMethod);
//                    }
//
//                    return "OK";
//                });
//
//        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
//
//        Gson gson = new Gson();
//        DBConnection dbConnection = new DBConnection();
//        Authentication auth = new Authentication("secret");
//        UserRepository userMySQLRepository = new UsersMySQLRepository(dbConnection.getConnection(), dbConnection.getStatement());
//        MessageRepository msgMYSQLRepository = new MessagesMySQLRepository(dbConnection.getConnection(), dbConnection.getStatement());
//          UserRepository userMySQLRepository= new UsersArrayListRepository();
        //-------------------------
        //*apis using DataBase Repo*//
        //-------------------------
        //login 
        post("/login", (req, res) -> {
//            try {
//                String userinfo = req.body();
//                User realUser = auth.AuthenticateWithJson(userMySQLRepository, userinfo);
//                String token = auth.generateToken(userinfo);
////                res.header("token", token);
//                realUser.setToken(token);
//                return gson.toJson(realUser);
//                if (realUser == null) {
//                    res.status(401);
//                    return "not user ? sign up now";
//                }

//                res.header("token", token);
//                res.header("Access-Control-Allow-Origin", "http://localhost:4567");
//                res.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//                res.header("Access-Control-Allow-Origin", "Content-Type, Authorization, Content-Length, X-Requested-With");
//                res.header("Access-Control-Allow-Credentials", "true");
//            } catch (AuthenticationError | DataBaseError ex) {
//                return ex;
//            }
            return "you ara in login api";
        });
        // sign up 
        post("/Signup", (req, res) -> {
//            try {
//                String userinfo = req.body();
//                User fakeUser = gson.fromJson(userinfo, User.class);
//                User newUser = userMySQLRepository.addUser(fakeUser.getUserName(), fakeUser.getPassword(), fakeUser.getE_mail(), fakeUser.getMobileNum());
////                if (newUser == null) {
////                    res.status(401);
////                    return " user name exist";
////                }
//                String token = auth.generateToken(userinfo);
//                res.header("token", token);
//                newUser.setToken(token);
//                return gson.toJson(newUser);
//            } catch (DataBaseError e) {
//                return e;
//            }
            return "signup api";

        });

//        delete user by user name 
        delete("/User", (req, res) -> {

//            try {
//
//                String token = req.headers("Authentication");
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
////                if (isUser == null) {
////                    return " unauthenticated";
////                }
//
//                User DeletedUser = userMySQLRepository.removeUserByUserName(isUser.getUserName());
//
//                return gson.toJson(DeletedUser);
//
//            } catch (DataBaseError | AuthenticationError e) {
//
//                return e;
//            }
            return "delete user apo";

        });

        //get allusers
        post("/Users", (req, res) -> {
//            try {
//                //String token = req.headers("Authentication");
//                String token = req.body();
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
//                ArrayList<User> allUsers = userMySQLRepository.getAllUsers();
//                return gson.toJson(allUsers);
//
////                System.out.println("token : " + token);
////                System.out.println("isUser : " + isUser);
////                if (isUser == null) {
////                    return "unauthenticated";
////                }
////                if (allUsers == null) {
////                    return "there is no users";
////                }
//            } catch (DataBaseError | AuthenticationError e) {
//                return e;
//            }
            return "get all users api";

        });

        // get all messages
        post("/messages", (req, res) -> {
//            try {
//                String token = req.body();
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
////                if (isUser == null) {
////                    res.status(401);
////                    return "unauthenticated";
////                }
//                String userName = isUser.getUserName();
//                ArrayList<Message> allMessages = msgMYSQLRepository.getAllMessages(userName);
//                System.out.print("messages: " + allMessages + "\n for user name: " + userName + "\n");
//                return gson.toJson(allMessages);
//
//            } catch (AuthenticationError | DataBaseError e) {
//
//                return e;
//            }
            return "get all messages api";
        });

        // send message
        post("/message", (req, res) -> {
//            try {
//                String newMessage = req.body();
//                Message sendedMessage = gson.fromJson(newMessage, Message.class);
//                // System.out.print("newMessage"+newMessage);
//                User isuser = auth.AuthenticateWithToken(userMySQLRepository, sendedMessage.getToken());
//                //System.out.print("sender"+isuser);
////            if (isuser == null) {
////                res.status(401);
////                return "unauthenticated";
////            }
//                User validReceiver = userMySQLRepository.getUserByUsername(sendedMessage.getReciver());
//                // System.out.print("rec"+validReceiver);
////            if (validReceiver == null) {
////                return "cannot find this user";
////            }
//                Message sendMessage = msgMYSQLRepository.sendMessages(sendedMessage.getMessageBody(), isuser.getUserName(), sendedMessage.getReciver());
//
//                return gson.toJson(sendMessage);
//            } catch (DataBaseError | AuthenticationError e) {
//                return e;
//            }
            return "send message api";
        });
//
//        //get messages by sender 
        get("Msgs/:username", (req, res) -> {
//            ArrayList<Message> sendedMessages = null;
//            try {
//                String token = req.headers("Authentication");
//                String isSender = req.params("username");
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
////            if (isUser == null) {
////                res.status(401);
////                return "unauthenticated";
////            }
//                if (isSender.equals(isUser.getUserName())) {
//                    sendedMessages = msgMYSQLRepository.getMessageBySender(isSender);
//                }
//                return gson.toJson(sendedMessages);
//
//            } catch (DataBaseError | AuthenticationError e) {
//                return e;
//            }
            return "this is message by " + ":usersname";
        });
//
        //remove messages by sender 
        delete("messages/:sender", (req, res) -> {
//            ArrayList<Message> deletedMsgs = null;
//            try {
//                String token = req.headers("Authentication");
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
//                String sender = req.params("sender");
////            if (isUser == null) {
////                res.status(401);
////                return "unauthenticated";
////            }
//                if (sender.equals(isUser.getUserName())) {
//                    deletedMsgs = msgMYSQLRepository.removeMessageBySender(sender);
//                    return gson.toJson(deletedMsgs);
//                }
//            } catch (DataBaseError | AuthenticationError e) {
//                return e;
//            }
//            return deletedMsgs;
            return "delete messages";
        });
        //get all messages by user name
        get("/Messages", (req, res) -> {
//            try {
//                String token = req.headers("Authentication");
//                System.out.print("token" + token);
//                User isUser = auth.AuthenticateWithToken(userMySQLRepository, token);
////            if (isUser == null) {
////                res.status(401);
////                return "unauthenticated";
////            }
//                String userName = isUser.getUserName();
//                ArrayList<Message> allMessages = msgMYSQLRepository.getAllMessages(userName);
//                return gson.toJson(allMessages);
//            } catch (DataBaseError | AuthenticationError e) {
//                return e;
//            }
            return "get all messages by user name";
        });

    }

}
