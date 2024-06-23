package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.GenericArrayType;
import java.util.Collections;
import java.util.List;

@Repository
public class DatabaseProductRepository implements ProductRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public DatabaseProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product add(Product product) {
        String sql = "insert into products (name, price, amount) values (:name, :price, :amount)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new BeanPropertySqlParameterSource(product);

        jdbcTemplate.update(sql, param, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);
        return product;
    }

    public Product findById(Long id) {
        return null;
    }

    public List<Product> findAll() {
        return Collections.emptyList();
    }

    public List<Product> findByName(String name) {
        return Collections.emptyList();
    }

    public Product update(Product product) {
        return null;
    }

    public void delete(Long id) {
        // do nothing
    }
}
