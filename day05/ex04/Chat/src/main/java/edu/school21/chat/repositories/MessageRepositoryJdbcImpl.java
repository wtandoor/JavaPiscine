package edu.school21.chat.repositories;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedEntityException;
import edu.school21.chat.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.ChatRoom;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class MessageRepositoryJdbcImpl extends JdbcTemplate implements MessagesRepository {
    private final String DATA_PATH = System.getenv("PWD") + "/Chat/src/main/resources/data.sql";

    public MessageRepositoryJdbcImpl(HikariDataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Optional<Message> findById(int id) {
        int userId = 0;
        int chatroomId = 0;
        try {
            userId = super.queryForObject("SELECT author FROM chat.messages WHERE id = " + id, Integer.class);
            chatroomId = super.queryForObject("SELECT chatroom FROM chat.messages WHERE id = " + id, Integer.class);
        }catch (EmptyResultDataAccessException e){
            System.err.println("Empty field in data base, check index");
            System.exit(-1);
        }
        User author = super.queryForObject(format("SELECT * FROM chat.users WHERE id = %d;", userId),
                (ResultSet rs, int rowNum) ->
                        new User(rs.getInt("id"),
                                rs.getString("login"),
                                rs.getString("password"))
            );
        ChatRoom chatRoom = super.queryForObject(format("SELECT * FROM chat.rooms WHERE id = %d;", chatroomId),
                (ResultSet rs, int rowNum) ->
                new ChatRoom(rs.getInt("id"), rs.getString("name"),
                        super.queryForObject(format("SELECT * FROM chat.users WHERE id = %d;;", rs.getInt("owner")),
                                (ResultSet rss, int rn) ->
                                    new User (rss.getInt("id"),
                                            rss.getString("login"),
                                            rss.getString("password"))
                                )
                )
        );

        Message message = super.queryForObject(format("SELECT * FROM chat.messages WHERE id = %d;", id),
                (ResultSet rs, int rowNum) ->
                        new Message(rs.getInt("id"), author, chatRoom,
                                rs.getString("text"), rs.getString("time")));
        return Optional.ofNullable(message);
    }

    public void save(Message message){
        String insertbuff = "INSERT INTO chat.messages (author, chatRoom, text, time) VALUES (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            super.update(con -> {
                PreparedStatement ps = con.prepareStatement(insertbuff, new String[] {"id"});
                ps.setInt(1, message.getSender().getId());
                ps.setInt(2, message.getChatRoom().getId());
                ps.setString(3, message.getMessage());
                ps.setString(4, message.getDate());
                return ps;
            },keyHolder);
        } catch (DataAccessException e){
            throw new NotSavedEntityException();
        }
        message.setMessageId(keyHolder.getKey().intValue() - 1);
    }

    @Override
    public void update(Message message) {
        super.update(format("UPDATE chat.messages SET text  = '%s' WHERE id = %d;", message.getMessage(), message.getMessageId()));
    }

    @Override
    public List<User> findAll(int page, int size) {
        if (page < 1)
            page = 0;
        return super.query(String.format("SELECT * FROM chat.users LIMIT %d OFFSET %d", size, (page - 1) * size),
                (rs, rownum)-> new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), new ArrayList<>(), new ArrayList<>()));
    }
}

