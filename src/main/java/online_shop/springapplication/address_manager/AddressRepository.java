package online_shop.springapplication.address_manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Address> getAll() {
        return jdbcTemplate.query("SELECT * FROM ADRESY", BeanPropertyRowMapper.newInstance(Address.class));
    }

    public void save(Address address) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("ADRESY").usingColumns("MIASTO", "KOD_POCZTOWY", "ULICA", "NUMER_DOMU", "NUMER_MIESZKANIA");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(address);
        insertActor.execute(param);
    }

    public Address get(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM ADRESY WHERE ID_ADRESU = ?",args,BeanPropertyRowMapper.newInstance(Address.class));
    }

    public void update(Address address) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(address);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update("UPDATE ADRESY SET MIASTO=:MIASTO, KOD_POCZTOWY=:KOD_POCZTOWY, ULICA=:ULICA, NUMER_DOMU=:NUMER_DOMU, NUMER_MIESZKANIA=:NUMER_MIESZKANIA WHERE ID_ADRESU=:ID_ADRESU",param);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ADRESY WHERE ID_ADRESU = ?", id);
    }

}