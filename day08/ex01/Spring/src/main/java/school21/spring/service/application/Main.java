package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties");
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        {
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        }
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        User user = new User(1, "kekis@gmail.com");
        User user1 = new User(2, "kekis22@gmail.com");
        usersRepository.save(user);
        usersRepository.save(user1);
        usersRepository.delete(1);
        System.out.println(usersRepository.findAll());
    }
}
