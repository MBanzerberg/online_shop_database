package online_shop.springapplication.shop_information_manager;

import java.util.List;

import online_shop.springapplication.address_manager.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ShopInformationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShopInformationRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ShopInformation> getAll() {
        return jdbcTemplate.query("SELECT * FROM SKLEPY_INTERNETOWE", BeanPropertyRowMapper.newInstance(ShopInformation.class));
    }

    public void save(ShopInformation shopInformation) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("SKLEPY_INTERNETOWE").usingColumns("NAZWA", "DATA", "ADRES_SKLEPU_WWW", "NUMER_DOMU", "NUMER_MIESZKANIA", "ID_ADRESU");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(shopInformation);
        insertActor.execute(param);
    }

    public ShopInformation get(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM SKLEPY_INTERNETOWE WHERE ID_SKLEPU = ?",args,BeanPropertyRowMapper.newInstance(ShopInformation.class));
    }

    public void update(ShopInformation shopInformation) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(shopInformation);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update("UPDATE SKLEPY_INTERNETOWE SET NAZWA=:NAZWA, DATA=:DATA, ADRES_SKLEPU_WWW=:ADRES_SKLEPU_WWW, ID_ADRESU=:ID_ADRESU WHERE ID_SKLEPU=:ID_SKLEPU",param);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM SKLEPY_INTERNETOWE WHERE ID_SKLEPU = ?", id);
    }

    public void getAddressID(int id) {

    }

}