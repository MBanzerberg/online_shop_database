package online_shop.springapplication.product_manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM PRODUKTY", BeanPropertyRowMapper.newInstance(Product.class));
    }

    public void save(Product product) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("PRODUKTY").usingColumns("NAZWA","CENA","SPECYFIKACJA","OPIS","ID_SKLEPU");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(product);
        insertActor.execute(param);
    }

    public Product get(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM PRODUKTY WHERE ID_PRODUKTU = ?",args,BeanPropertyRowMapper.newInstance(Product.class));
    }

    public void update(Product product) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(product);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update("UPDATE PRODUKTY SET NAZWA=:NAZWA, CENA=:CENA," +
                " SPECYFIKACJA=:SPECYFIKACJA, OPIS=:OPIS, ID_SKLEPU=:ID_SKLEPU WHERE ID_PRODUKTU=:ID_PRODUKTU",param);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PRODUKTY WHERE ID_PRODUKTU = ?", id);
    }

}

