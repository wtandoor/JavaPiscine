package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UserRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;

import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
//        System.out.println(usersRepository.findAll());
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
//        System.out.println(usersRepository.findAll());
        User user = new User(1, "kekis@gmail.com");
        usersRepository.save(user);
        UsersService userService = context.getBean(UsersService.class);
         System.out.println(userService.signUp("kekis@gmail.com"));
        System.out.println(userService.signUp("asd@gmail.com"));
    }
}
