package edu.school21.chat.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Message {
    private final int messageId;
    private final User sender;
    private final ChatRoom chatRoom;
    private final String message;
    private final String date;
    private final String path = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public Message(User sender, ChatRoom chatRoom, String message, String date) {
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
        this.messageId = MessageIdsGenerator.getInstance().generateId();
        this.date = date;
        this.chatRoom.addMessageInChatRoom(this);
        String command = "insert into chat.messages (author, chatroom, text, time) values(" +
                '\'' + this.sender.getId() + '\'' + ", " + '\'' + this.chatRoom.getId() + '\''+
                ", " + '\'' + this.message + '\'' + ", " + '\'' + this.date + '\'' + ");\n";
        File log = new File(path);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(command);
            out.close();
        }catch(IOException e){
            System.out.println("invalid data.sql path(message)");
        }
    }

    public Message(int id, User sender, ChatRoom chatRoom, String message, String date) {
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
        this.messageId = id;
        this.date = date;
        this.chatRoom.addMessageInChatRoom(this);
        String command = "insert into chat.messages (author, chatroom, text, time) values(" +
                '\'' + this.sender.getId() + '\'' + ", " + '\'' + this.chatRoom.getId() + '\''+
                ", " + '\'' + this.message + '\'' + ", " + '\'' + this.date + '\'' + ");\n";
        File log = new File(path);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(command);
            out.close();
        }catch(IOException e){
            System.out.println("invalid data.sql path(message)");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return messageId == message1.messageId && Objects.equals(sender, message1.sender) && Objects.equals(chatRoom, message1.chatRoom) && Objects.equals(message, message1.message) && Objects.equals(date, message1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, sender, chatRoom, message, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sender=" + sender +
                ", chatRoom=" + chatRoom +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getMessageId(){
        return this.messageId;
    }
}