package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductRepositoriesJdbcImplTest {
    private Connection connection;
    private ProductsRepository productsRepository;
    final List<Product> FIND_ALL_PRODUCTS;

    public ProductRepositoriesJdbcImplTest() {
        Product product = new Product(0, "qwerty", 1000);
        Product product1 = new Product(1, "12345" , 2000);
        Product product2 = new Product(2, "zxcvb" , 3000);
        Product product3 = new Product(3, "asdfg",4000);
        Product product4 = new Product(4, "uiop", 5000);
        FIND_ALL_PRODUCTS = new LinkedList<>();
        FIND_ALL_PRODUCTS.add(product);
        FIND_ALL_PRODUCTS.add(product1);
        FIND_ALL_PRODUCTS.add(product2);
        FIND_ALL_PRODUCTS.add(product3);
        FIND_ALL_PRODUCTS.add(product4);
    }

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "12345", 2000);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2, "zxcvb", 3000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(5, "asfda", 6000);

    @BeforeEach
    public void setConnection() throws SQLException {
        this.connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build().getConnection();
        this.productsRepository = new ProductsRepositoryJdbcImpl(connection);
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(productsRepository.findById(1L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void testFindAll() {
        Assertions.assertIterableEquals(FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    public void testUpdate() {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById((long) EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    public void testSave() {
        EXPECTED_SAVED_PRODUCT.setId(6);
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById((long)6).get());
    }

    @Test
    public void testDelete() {
        productsRepository.delete((long)2);
        EmptyResultDataAccessException thrown = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productsRepository.findById((long)2);
        });
        System.out.println(thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains("Incorrect result size"));
    }
}
