package edu.school21.sockets.config;

import edu.school21.sockets.repositories.UserRepositoryJdbcTemplateImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
//@PropertySource("db.properties")
//@ComponentScan(basePackages = "edu.school21.sockets")
public class SocketApplicationConfig {
//    @Value("${db.url}")
//    private String url;
//    @Value("${db.user}")
//    private String user;
//    @Value("${db.password}")
//    private String password;
//    @Value("${db.driver.name}")
//    private String driverName;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setPassword("postgres");
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        return dataSource;
    }

    @Bean
    public UserRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
        return new UserRepositoryJdbcTemplateImpl(dataSource(), "users");
    }

    @Bean("Service")
    public  UsersService usersService(){
        return new UsersServiceImpl();
    }
}