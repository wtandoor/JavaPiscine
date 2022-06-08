package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryJdbcImpl implements UsersRepository{
    public DataSource dataSource;
    private final String table;


    public UsersRepositoryJdbcImpl(DataSource dataSource, String table) {
        this.dataSource = dataSource;
        this.table = table;
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(String.format("SELECT * FROM %s WHERE email = '%s'", table, email));
        if (!resultSet.next()){
            return Optional.empty();
        }
        User user = new User(resultSet.getLong("identifier"), resultSet.getString("email"));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findById(long id) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(String.format("SELECT * FROM %s WHERE identifier = %d", table, id));
        if (!resultSet.next()){
            return Optional.empty();
        }
        User user = new User(resultSet.getLong("identifier"), resultSet.getString("email"));
        return Optional.of(user);
    }


    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(String.format("SELECT ALL * FROM %s", table));
        while (resultSet.next()){
            userList.add(new User(resultSet.getInt("identifier"), resultSet.getString("email")));
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        String command = String.format("INSERT INTO %s (identifier, email) VALUES (%d, '%s');", table, entity.getIdentifier(), entity.getEmail());
        try {
            dataSource.getConnection().createStatement().execute(command);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) throws SQLException {
        String command = String.format("UPDATE %s SET text = '%s WHERE identifier = %d;", table, entity.getEmail(), entity.getIdentifier());
        dataSource.getConnection().createStatement().execute(command);
    }

    @Override
    public void delete(long id) throws SQLException {
        String command = String.format("DELETE FROM %s WHERE identifier = %d", table, id);
        dataSource.getConnection().createStatement().execute(command);
    }
}
