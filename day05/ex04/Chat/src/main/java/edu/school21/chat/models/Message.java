package edu.school21.chat.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Message {
    private  int messageId;
    private final User sender;
    private final ChatRoom chatRoom;
    private String message;
    private String date;
    private final String path = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public Message(User sender, ChatRoom chatRoom, String message, String date) {
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
        this.messageId = MessageIdsGenerator.getInstance().generateId();
        this.date = date;
        this.chatRoom.addMessageInChatRoom(this);
    }

    public Message(int id, User sender, ChatRoom chatRoom, String message, String date) {
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
        this.messageId = id;
        this.date = date;
        this.chatRoom.addMessageInChatRoom(this);
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

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
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