package edu.school21.chat.repositories;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.ChatRoom;

import java.sql.ResultSet;
import java.util.Optional;

import static java.lang.String.format;

public class MessageRepositoryJdbcImpl extends JdbcTemplate implements MessagesRepository {

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
}