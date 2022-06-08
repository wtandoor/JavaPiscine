package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class ProductsRepositoryJdbcImpl extends JdbcTemplate implements ProductsRepository{
    private JdbcTemplate jdbcTemplate;

    public ProductsRepositoryJdbcImpl(Connection connection) {
        this.jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
    }

    @Override
    public List<Product> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM product", (rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price")));
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = this.jdbcTemplate.queryForObject(format("SELECT * FROM product WHERE id = %d;", id),
                (rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("price")));
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) {
        this.jdbcTemplate.update(format("UPDATE product " + "SET name = '%s', price = %d " + "WHERE id = %d;", product.getName(), product.getPrice(), product.getId()));
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update(format("INSERT INTO product (id, name, price) " + "VALUES (%d, '%s', %d);", product.getId(), product.getName(), product.getPrice()));
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(format("DELETE FROM product WHERE id = %d;", id));
    }
}
