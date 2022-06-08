package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import

import javax.sql.DataSource;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbeddedDataSourceTest {
    DataSource dataSource;

    @BeforeEach
    public void initialization(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        dataSource = databaseBuilder.setType(EmbeddedDatabaseType.HSQL).addScript("./schema.sql").addScript("./data.sql").build();
    }
    @Test
    public void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }

}
