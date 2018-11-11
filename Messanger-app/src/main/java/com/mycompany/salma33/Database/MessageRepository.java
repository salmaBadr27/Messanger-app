package com.mycompany.salma33.Database;

import com.mycompany.salma33.Application.DataBaseError;
import com.mycompany.salma33.Models.Message;
import java.util.ArrayList;

abstract public class MessageRepository {

    abstract public ArrayList getAllMessages(String username) throws DataBaseError;

    abstract public Message sendMessages(String body, String sender, String receiver)throws DataBaseError;

    abstract public ArrayList getMessageBySender(String sender)throws DataBaseError;

    abstract public ArrayList getMessageByReceiver(String receiver)throws DataBaseError;

    abstract public ArrayList removeMessageBySender(String sender)throws DataBaseError;

}
