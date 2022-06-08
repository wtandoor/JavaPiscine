package edu.school21.chat.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private final String name;
    private final User creator;
    private List<Message> messageList = new ArrayList<>();
    private final int id;
    private final String path = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public ChatRoom(String name, User creator) {
        this.name = name;
        this.creator = creator;
        this.id = ChatRoomsIdsGenerator.getInstance().generateId();
        this.creator.addChatroomCreated(this);
        String command = "insert into chat.rooms (name, owner) values(" +
                '\'' + this.name + '\'' + ", " + '\'' + this.creator.getId() + '\''+  ");\n";
        File log = new File(path);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(command);
            out.close();
        }catch(IOException e){
            System.out.println("invalid data.sql path(chatroom)");
        }
    }

    public ChatRoom(int id, String name, User creator) {
        this.name = name;
        this.creator = creator;
        this.id = id;
        this.creator.addChatroomCreated(this);
        String command = "insert into chat.rooms (name, owner) values(" +
                '\'' + this.name + '\'' + ", " + '\'' + this.creator.getId() + '\''+  ");\n";
        File log = new File(path);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(command);
            out.close();
        }catch(IOException e){
            System.out.println("invalid data.sql path(chatroom)");
        }
    }

    public void addMessageInChatRoom(Message message){
        messageList.add(message);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return id == chatRoom.id && Objects.equals(name, chatRoom.name) && Objects.equals(creator, chatRoom.creator) && Objects.equals(messageList, chatRoom.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creator, messageList, id);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", creator=" + creator + "id='" + id +
                '}';
    }

    public String getName(){
        return name;
    }
    public int getId(){return this.id;}
}