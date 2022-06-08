package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {
    private DataSource dataSource;
    private final String tablename;
    private int countOfSavedUsers;

    public int getCountOfSavedUsers() {
        return countOfSavedUsers;
    }
    public UserRepositoryJdbcTemplateImpl(DataSource dataSource, String tablename) {
        this.dataSource = dataSource;
        this.tablename = tablename;
        super.setDataSource(dataSource);
        this.countOfSavedUsers = 0;
    }

    @Override
    public Optional<User> findById(long id) throws SQLException {
        User user = super.queryForObject(String.format("SELECT * FROM %s WHERE identifier = %d", tablename, id), ((rs, rowNum) -> new User(rs.getLong("identifier"), rs.getString("email"), rs.getString("password"))));
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return super.query(String.format("SELECT ALL * FROM %s", tablename), (rs, rowNum) -> new User(rs.getLong("identifier"), rs.getString("email"), rs.getString("password")));
    }

    @Override
    public void save(User entity) {
        if (entity.getPassword().equals(" "))
            entity.setPassword(new UsersServiceImpl().generatePassword());
        super.update(String.format("INSERT INTO %s VALUES (%d, '%s', '%s')", tablename, entity.getId(), entity.getEmail(), entity.getPassword()));
        countOfSavedUsers++;
    }

    @Override
    public void update(User entity) throws SQLException {
        super.update(String.format("UPDATE %s SET password = '%s' WHERE email = '%s'", tablename, entity.getPassword(), entity.getEmail()));
    }

    @Override
    public void delete(long id) throws SQLException {
        super.update(String.format("DELETE FROM %s WHERE identifier = %d", tablename, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(super.queryForObject(String.format("SELECT * FROM %s WHERE email = '%s'", tablename, email),
                    (rs, rowNum) -> new User(rs.getLong("identifier"), rs.getString("email"), rs.getString("password"))));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public String findPasswordByEmail(String email) {
        return super.queryForObject(String.format("SELECT * FROM %s WHERE email = '%s'", tablename, email), (rs, rowNum) -> new String(rs.getString("password")));
    }
}