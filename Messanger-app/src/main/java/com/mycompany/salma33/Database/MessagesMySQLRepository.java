package com.mycompany.salma33.Database;

import com.mycompany.salma33.Application.DataBaseError;
import com.mycompany.salma33.Models.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class MessagesMySQLRepository extends MessageRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public MessagesMySQLRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public ArrayList getAllMessages(String username) throws DataBaseError {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + username + "' or receiver = '" + username + "' order by created_at asc ";
            result = statement.executeQuery(query);
//            if (result.next() == false) {
//                throw new DataBaseError("no matched User Founded");
//
//            } else {
            while (result.next()) {

                String msgId = result.getString("Msg_Id");
                String msgBody = result.getString("Msg_body");
              String sender = result.getString("sender");
                String receiver = result.getString("receiver");
                Timestamp created_at = result.getTimestamp("created_at");
                Message message = new Message(msgId, msgBody, sender, receiver, created_at);
                totalMessages.add(message);

            }
            return totalMessages;
//            }
        } catch (SQLException ex) {

            throw new DataBaseError(ex.getMessage());
        }

    }

    @Override
    public Message sendMessages(String body, String msgsender, String msgreceiver) throws DataBaseError {

        try {
            String msgid = UUID.randomUUID().toString();
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            Message newMsg = new Message(msgid, body, msgsender, msgreceiver, created_at);
            statement.executeUpdate("INSERT INTO messages (Msg_Id, msg_body, sender, receiver,created_at) VALUES ('" + msgid + "','" + body + "','" + msgsender + "','" + msgreceiver + "','" + created_at + "')");
            return newMsg;
        } catch (SQLException ex) {
            throw new DataBaseError(ex.getMessage());

        }

    }

    @Override
    public ArrayList getMessageBySender(String sender) throws DataBaseError {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + sender + "'";
            result = statement.executeQuery(query);
            if (result.next() == false) {

                throw new DataBaseError("no matched username found");
            } else {
                do {
                    String msgBody = result.getString("Msg_body");
                    String msgSender = result.getString("sender");
                    String msgReceiver = result.getString("receiver");
                    Timestamp created_at = result.getTimestamp("created_at");
                    Message sendedMessage = new Message(msgBody, msgSender, msgReceiver, created_at);
                    totalMessages.add(sendedMessage);
                    return totalMessages;
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("caught in MsgMySql " + ex);

            throw new DataBaseError(ex.getMessage());
        }

    }

    @Override
    public ArrayList getMessageByReceiver(String receiver) throws DataBaseError {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + receiver + "' ";
            result = statement.executeQuery(query);
            if (result.next()) {

                throw new DataBaseError("no matched username found");
            } else {
                do {
                    String msgBody = result.getString("Msg_body");
                    String msgSender = result.getString("sender");
                    String msgReceiver = result.getString("receiver");
                    Timestamp created_at = result.getTimestamp("created_at");
                    Message receivedMessage = new Message(msgBody, msgSender, msgReceiver, created_at);
                    totalMessages.add(receivedMessage);
                    return totalMessages;
                } while (result.next());
            }

        } catch (SQLException ex) {
            throw new DataBaseError(ex.getMessage());

        }

    }

    @Override
    public ArrayList removeMessageBySender(String sender) throws DataBaseError {
        ArrayList<Message> deletedMessages = new ArrayList();
        try {
            String qyery = " select * from messages where sender = '" + sender + "' ";
            result = statement.executeQuery(qyery);
            if (result.next() == false) {
                throw new DataBaseError("no matched username found");
            } else {
                do {
                    String msgBody = result.getString("Msg_body");
                    String msgSender = result.getString("sender");
                    String msgReceiver = result.getString("receiver");
                    Timestamp created_at = result.getTimestamp("created_at");
                    Message sendedMessage = new Message(msgBody, msgSender, msgReceiver, created_at);
                    deletedMessages.add(sendedMessage);
                    statement.executeUpdate("delete from messages where sender = '" + sender + "'");
                    return deletedMessages;
                } while (result.next());
            }

        } catch (SQLException | DataBaseError ex) {
            throw new DataBaseError(ex.getMessage());

        }

    }
}
