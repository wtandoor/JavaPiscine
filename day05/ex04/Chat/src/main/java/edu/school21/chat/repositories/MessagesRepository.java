package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface MessagesRepository {
    Optional<Message> findById(int id);
    void save(Message message);
    void update(Message message);
    List<User> findAll(int page, int size);
}
