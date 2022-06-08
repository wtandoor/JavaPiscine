package edu.school21.chat.models;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private List<ChatRoom> createdRooms = new ArrayList<>();
    private List<ChatRoom> activeRooms = new ArrayList<>();
    private final String password;
    private final String login;
    private final int id;
    private final String path = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public User(String login, String password) {
        this.password = password;
        this.login = login;
        this.id = UserIdsGenerator.getInstance().generateId();

    }

    public User(int id,String login, String password) {
        this.password = password;
        this.login = login;
        this.id = id;
//        String command = "insert into chat.users (login, password) values(" +
//                '\'' + this.login + '\'' + ", " + this.password + ");\n";
//        File log = new File(path);
//        try{
//            PrintWriter out = new PrintWriter(new FileWriter(log, true));
//            out.append(command);
//            out.close();
//        }catch(IOException e){
//            System.out.println("invalid data.sql path(user)");
//        }

    }

    public void addChatroomCreated(ChatRoom chatRoom){
        createdRooms.add(chatRoom);
        activeRooms.add(chatRoom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(activeRooms, user.activeRooms) && Objects.equals(password, user.password) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdRooms, activeRooms, password, login, id);
    }

    @Override
    public String toString() {
        return "{" +
                "password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", id=" + id +
                '}';
    }

    public String getLogin(){
        return this.login;
    }

    public int getId() {
        return id;
    }
}