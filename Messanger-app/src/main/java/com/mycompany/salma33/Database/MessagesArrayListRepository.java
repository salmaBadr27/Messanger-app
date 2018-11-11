package com.mycompany.salma33.Database;

import com.mycompany.salma33.Models.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.UUID;

public class MessagesArrayListRepository extends MessageRepository {

    ArrayList<Message> messages = new ArrayList<Message>();
    Gson gson = new Gson();

    public MessagesArrayListRepository() {
        this.messages = new ArrayList<Message>();
    }

    @Override
    public ArrayList getAllMessages(String username) {
        ArrayList<Message> totalMessages = new ArrayList<Message>();

        for (Message msg : messages) {
            if (msg.getSender().equals(username) || msg.getReciver().equals(username)) {
                totalMessages.add(msg);
            }
        }

        return totalMessages;
    }

    @Override
    public Message sendMessages(String body, String sender, String receiver) {
        String msgid = UUID.randomUUID().toString();
        Message msg = new Message( msgid ,body, sender, receiver);
        messages.add(msg);
        return msg;

    }

    @Override
    public ArrayList getMessageBySender(String sender) {
        ArrayList<Message> messageBySender = new ArrayList<Message>();

        for (Message msg : messages) {
            if (msg.getSender().equals(sender)) {
                messageBySender.add(msg);
            }
        }

        return messageBySender;
    }

    @Override
    public ArrayList getMessageByReceiver(String receiver) {
        ArrayList<Message> messageByReceiver = new ArrayList<Message>();
        for (Message msg : messages) {
            if (msg.getReciver().equals(receiver));
            {
                messageByReceiver.add(msg);
            }
        }
        return messageByReceiver;
    }

    @Override
    public ArrayList removeMessageBySender(String sender) {
        ArrayList<Message> sendedMessages = new ArrayList<Message>();
        for (Message msg : messages) {
            if (msg.getSender().equals(sender)) {
                sendedMessages.add(msg);
                messages.remove(sendedMessages);
            }
        }
        return sendedMessages;
    }

}
