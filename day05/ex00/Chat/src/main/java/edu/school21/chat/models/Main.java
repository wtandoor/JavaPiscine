package edu.school21.chat.models;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "postgres";
    private static final String DB_SCHEMA = "resources/schema.sql";
    private static final String DB_DATA = "resources/data.sql";
    private static final String DB_DATA_PATH = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";;
    private static final String DB_RESOURSES_PATH = System.getenv("PWD") + "/Chat/src/main/";
    private static final String DB_SCHEMA_PATH = System.getenv("PWD") + "/Chat/src/main/resources/schema.sql";

    public static void main(String[] args) throws FileNotFoundException {
        dosmth();
        Connection connection = connect();
        System.out.println("Creating tables...");
        runQueriesFromFile(connection, DB_SCHEMA);
        System.out.println("Tables created!");
        System.out.println("Populating tables!");
        runQueriesFromFile(connection, DB_DATA);
        System.out.println("Data inserted");
    }

    public static void dosmth(){
        File log = new File(DB_DATA_PATH);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(log, false));
            out.print("");
            out.close();
        }catch(IOException e){
            System.out.println("invalid data.sql path(user)");
        }
        Date date = new Date();
        User Roma = new User("Roma", "123");
        User Dinar = new User("Dinar", "12345");
        User Nastya = new User("Nastya", "12386");
        User Masha = new User("Masha", "12334");
        User Aidar = new User("Aidar" , "12343");
        ChatRoom firstLobby = new ChatRoom("LobbyZXC", Roma);
        ChatRoom secondLobby = new ChatRoom("Lobby2" , Dinar);
        ChatRoom thirdLobby = new ChatRoom("Lobby3", Nastya);
        ChatRoom fourthLobby = new ChatRoom("Lobby4", Masha);
        ChatRoom fifthLobby = new ChatRoom("Lobby5", Aidar);
        Message firstMessage = new Message(Roma, firstLobby, "go lobby 1x1!", date.toString());
        Message secondMessage = new Message(Dinar, secondLobby, "go", date.toString());
        Message thirdMessage = new Message(Roma, thirdLobby, "go3", date.toString());
        Message fourthMessage = new Message(Masha, fourthLobby, "go4", date.toString());
        Message fifthMessage = new Message(Aidar, fifthLobby, "go5", date.toString());
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void runQueriesFromFile(Connection connection, String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new File(DB_RESOURSES_PATH  + filename))
                .useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        scanner.close();
    }
}