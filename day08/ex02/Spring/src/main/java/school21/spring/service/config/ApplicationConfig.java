package school21.spring.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.repositories.UserRepositoryJdbcTemplateImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

@Configuration
@PropertySource("db.properties")
@ComponentScan(basePackages = "school21.spring.service")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driverName;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(password);
        dataSource.setUsername(user);
        dataSource.setUrl(url);
        return dataSource;
    }
    @Bean
    public UserRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate(){
        return new UserRepositoryJdbcTemplateImpl(dataSource(), "users");
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc(){
       return new UsersRepositoryJdbcImpl(dataSource(), "users");
    }


}
