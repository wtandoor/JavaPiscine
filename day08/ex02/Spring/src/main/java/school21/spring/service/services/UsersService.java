package school21.spring.service.services;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public interface UsersService {
    String signUp(String email) throws SQLException;
}
