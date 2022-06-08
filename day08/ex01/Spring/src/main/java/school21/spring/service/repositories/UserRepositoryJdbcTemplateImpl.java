package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {
    private DataSource dataSource;
    private final String tablename;

    public UserRepositoryJdbcTemplateImpl(DataSource dataSource, String tablename) {
        this.dataSource = dataSource;
        this.tablename = tablename;
        super.setDataSource(dataSource);
    }

    @Override
    public Optional<User> findById(long id) {
        User user = super.queryForObject(String.format("SELECT * FROM %s WHERE identifier = %d", tablename, id),
                ((rs, rowNum) -> new User(rs.getLong("identifier"), rs.getString("email"))));
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return super.query(String.format("SELECT ALL * FROM %s", tablename),
                (rs, rowNum) -> new User(rs.getInt("identifier"), rs.getString("email")));
    }

    @Override
    public void save(User entity) throws SQLException {
        String command = String.format("INSERT INTO %s (identifier, email) VALUES (%d, '%s');", tablename, entity.getIdentifier(), entity.getEmail());
        super.update(command);
    }

    @Override
    public void update(User entity) {
        String command = String.format("UPDATE %s SET text = '%s WHERE identifier = %d;", tablename, entity.getEmail(), entity.getIdentifier());
        super.update(command);
    }

    @Override
    public void delete(long id) {
        String command = String.format("DELETE FROM %s WHERE identifier = %d", tablename, id);
        super.update(command);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = super.queryForObject(String.format("SELECT * FROM %s WHERE email = '%d'", tablename, email),
                ((rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"))));
        return Optional.ofNullable(user);
    }
}
