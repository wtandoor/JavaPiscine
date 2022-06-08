package edu.school21.sockets.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(long id) throws SQLException;
    List<T> findAll() throws SQLException;
    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(long id) throws SQLException;
}
