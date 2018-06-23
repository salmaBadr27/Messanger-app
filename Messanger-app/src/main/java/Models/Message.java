package Models;

import java.sql.Date;
import java.sql.Timestamp;

public class Message {
    // instances

    private  String  messageId;
    private String messageBody;
    private  String sender;
    private  String reciver;
    private Timestamp created_at;

    //Constructors
    public Message( String messageBody, String msgSender, String msgReciver,Timestamp created_at) {

        this.messageBody = messageBody;
        this.sender = msgSender;
        this.reciver = msgReciver;
        this.created_at = created_at;
    }

    public Message(String messageId, String messageBody, String sender, String reciver) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.sender = sender;
        this.reciver = reciver;
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
        return this.reciver;
    }

    // Message.toString()
    @Override
    public String toString() {
        return "From:" + sender + "\n" + "To:" + reciver + "\n" + "Subject::" + messageBody;
    }

}
