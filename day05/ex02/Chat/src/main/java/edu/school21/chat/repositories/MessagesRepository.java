package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.util.Optional;
import java.sql.SQLException;

public interface MessagesRepository {
    Optional<Message> findById(int id);
    void save(Message message);
}
