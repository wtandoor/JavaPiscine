package edu.school21.sockets.services;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

public interface UsersService {
    boolean signIn(String email, String password);
}
