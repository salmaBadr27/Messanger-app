package Database;

import Models.Message;
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
    public ArrayList getAllMessages(String username) {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + username + "' or receiver = '" + username + "'";
            result = statement.executeQuery(query);
            while (result.next()) {
                String msgBody = result.getString("Msg_body");
                String sender = result.getString("sender");
                String receiver = result.getString("receiver");
                String created_at = result.getString("created_at");

                Message message = new Message(msgBody, sender, receiver,created_at);
                totalMessages.add(message);
            }

        } catch (Exception ex) {

            System.out.print(ex);
        }
        return totalMessages;
    }

    @Override
    public Message sendMessages(String body, String msgsender, String msgreceiver) {

        try {
            String msgid = UUID.randomUUID().toString();
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            Message newMsg = new Message(body, msgsender, msgreceiver,created_at);
            statement.executeUpdate("INSERT INTO messages (Msg_Id, msg_body, sender, receiver,created_at) VALUES ('" + msgid + "','" + body + "','" + msgsender + "','" + msgreceiver + "','"+created_at+"')");
            return newMsg;
        } catch (Exception ex) {

            System.out.print(ex);
        }

        return null;
    }

    @Override
    public ArrayList getMessageBySender(String sender) {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + sender + "'";
            result = statement.executeQuery(query);
            while (result.next()) {
                String msgBody = result.getString("Msg_body");
                String msgSender = result.getString("sender");
                String msgReceiver = result.getString("receiver");
                String created_at = result.getString("created_at");
                Message sendedMesage = new Message(msgBody, msgSender, msgReceiver,created_at);
                totalMessages.add(sendedMesage);
            }

        } catch (Exception ex) {

            System.out.print(ex);
        }
        return totalMessages;
    }

    @Override
    public ArrayList getMessageByReceiver(String receiver) {
        ArrayList<Message> totalMessages = new ArrayList();
        try {
            String query = " select * from messages where sender = '" + receiver + "' ";
            result = statement.executeQuery(query);
            while (result.next()) {
                String msgBody = result.getString("Msg_body");
                String msgSender = result.getString("sender");
                String msgReceiver = result.getString("receiver");
                String created_at = result.getString("created_at");
                Message receivedMessage = new Message(msgBody, msgSender, msgReceiver,created_at);
                totalMessages.add(receivedMessage);
            }

        } catch (Exception ex) {

            System.out.print(ex);
        }
        return totalMessages;

    }

    @Override
    public ArrayList removeMessageBySender(String sender) {
        ArrayList<Message> deletedMessages = new ArrayList();
        try {
            String qyery = " select * from messages where sender = '" + sender + "' ";
            result = statement.executeQuery(qyery);
            while (result.next()) {
                String msgBody = result.getString("Msg_body");
                String msgSender = result.getString("sender");
                String msgReceiver = result.getString("receiver");
                String created_at = result.getString("created_at");
                Message sendedMessage = new Message(msgBody, msgSender, msgReceiver,created_at);
                deletedMessages.add(sendedMessage);
            }
            statement.executeUpdate("delete from messages where sender = '" + sender + "'");

        } catch (Exception ex) {

            System.out.print(ex);
        }
        return deletedMessages;
    }
}
