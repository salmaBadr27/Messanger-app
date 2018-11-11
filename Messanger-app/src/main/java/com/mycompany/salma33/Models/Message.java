package com.mycompany.salma33.Models;

import java.sql.Timestamp;

public class Message {
    // instances

    private  String  messageId;
    private String messageBody;
    private  String sender;
    private  String receiver;
    private Timestamp created_at;
    private String token;

    //Constructors
    public Message( String messageBody, String msgSender, String msgReciver,Timestamp created_at) {

        this.messageBody = messageBody;
        this.sender = msgSender;
        this.receiver = msgReciver;
        this.created_at = created_at;
    }

    public Message(String messageId, String messageBody, String sender, String receiver,Timestamp created_at) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.sender = sender;
        this.receiver = receiver;
         this.created_at = created_at;
    }
    
 public Message( String messageBody, String sender, String receiver) {
      
        this.messageBody = messageBody;
        this.sender = sender;
        this.receiver = receiver;
         
    }

    public Message(String messageId,String messageBody, String receiver, String token) {
          this.messageId = messageId;
        this.messageBody = messageBody;
        this.receiver = receiver;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public String getToken() {
        return token;
    }
 
    
    //Getters and Setters
    public String getMessageId() {
        return this.messageId;

    }

    public String getMessageBody() {
        return this.messageBody;

    }

    public void setMessageBody(String msgBody) {
        this.messageBody = msgBody;
    }

    public String getSender () {
        return this.sender;
    }

    public String getReciver () {
        return this.receiver;
    }

    // Message.toString()
    @Override
    public String toString() {
        return "From:" + sender + "\n" + "To:" + receiver + "\n" + "Subject::" + messageBody;
    }

}
