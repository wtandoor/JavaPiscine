package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsersReposytoryJdbcImpl extends JdbcTemplate implements  UserRepository {
    public UsersReposytoryJdbcImpl(HikariDataSource dataSource) {
        super.setDataSource(dataSource);
    }
    @Override
    public List<User> findAll(int page, int size) {
        if (page < 1)
            page = 0;
        return super.query(String.format("SELECT * FROM chat.users LIMIT %d OFFSET %d", size, (page - 1) * size),
                (rs, rownum)-> new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), new ArrayList<>(), new ArrayList<>()));
    }
}
