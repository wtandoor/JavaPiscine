package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "postgres";
    private static final String DB_SCHEMA_PATH = System.getenv("PWD") + "/Chat/src/main/resources/schema.sql";
    private static final String DB_DATA_PATH = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public static HikariDataSource datasource() throws SQLException, IOException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PWD);
        dataSource.setDriverClassName("org.postgresql.Driver");
        Statement st = dataSource.getConnection().createStatement();
        st.execute(new String(Files.readAllBytes(Paths.get(DB_SCHEMA_PATH))));
        st.execute(new String(Files.readAllBytes(Paths.get(DB_DATA_PATH))));
        return dataSource;
    }

    public static void main(String[] args) throws IOException, SQLException {
        dosmth();
    }

    public static void dosmth() throws SQLException, IOException {
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

        MessagesRepository messagesRepository = new MessageRepositoryJdbcImpl(datasource());
        UserRepository userRepository = new UsersReposytoryJdbcImpl(datasource());
        System.out.println(userRepository.findAll(1, 3));
        messagesRepository.save(fifthMessage);
        messagesRepository.save(firstMessage);
        messagesRepository.save(thirdMessage);
        messagesRepository.save(secondMessage);
        messagesRepository.save(fourthMessage);
        List<User> users = messagesRepository.findAll(1, 3);
        for (User s : users)
            System.out.println(s);

    }
}
