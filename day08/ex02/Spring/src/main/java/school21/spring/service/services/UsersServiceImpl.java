package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{
    @Autowired
    @Qualifier("usersRepositoryJdbcTemplate")
    UsersRepository usersRepository;
    @Override
    public String signUp(String email) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        String password;
        if (optionalUser.isPresent()) {
            password = usersRepository.findPasswordByEmail(email);
            return password;
        } else {
            password = generatePassword();
            usersRepository.savePassword(email, password);
        }
        return password;
    }

    public String generatePassword(){
        int len = 10;
        String password = "";
        for (int i = 0; i < len; i++){
            password += (char)(Math.random() * 95 + 32);
        }
        return password;
    }
}
