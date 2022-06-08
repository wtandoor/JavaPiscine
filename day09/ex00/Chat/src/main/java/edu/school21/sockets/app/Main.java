package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UserRepositoryJdbcTemplateImpl;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketApplicationConfig.class);
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
//        usersRepository.save(new User(1, "kekis@gmail.com", "password"));
        usersRepository.save(new User(2, "qwerty@gmail.com", "password"));
        usersRepository.save(new User(3, "12345@gmail.com", "password"));
        Server server = new Server(8081);
        server.startServer();
    }
}
