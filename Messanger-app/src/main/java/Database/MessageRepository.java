package Database;

import Models.Message;
import java.util.ArrayList;

abstract public class MessageRepository {

    abstract public ArrayList getAllMessages(String username);

    abstract public Message sendMessages(String body, String sender, String receiver);

    abstract public ArrayList getMessageBySender(String sender);

    abstract public ArrayList getMessageByReceiver(String receiver);

    abstract public ArrayList removeMessageBySender(String sender);

}
