package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    @Qualifier("usersRepositoryJdbcTemplate")
    UsersRepository usersRepository;
    @Override
    public boolean signUp(String email, String password) throws SQLException {
        try {
            Optional<User> optionalUser = usersRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                if (optionalUser.get().getPassword().equals(password)) {
                    return true;
                } else
                    return false;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e){
            return false;
        }
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
