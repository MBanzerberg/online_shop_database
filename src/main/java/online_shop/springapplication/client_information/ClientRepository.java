package online_shop.springapplication.client_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getAll() {
        return jdbcTemplate.query("SELECT * FROM KLIENCI", BeanPropertyRowMapper.newInstance(Client.class));
    }

    public void save(Client client) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("KLIENCI").usingColumns("HASLO", "IMIE", "NAZWISKO", "EMAIL", "NR_TELEFONU", "PLEC", "ID_SKLEPU", "ID_ADRESU");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(client);
        insertActor.execute(param);
    }

    public Client get(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM KLIENCI WHERE ID_KLIENTA = ?",args,BeanPropertyRowMapper.newInstance(Client.class));
    }

    public void update(Client client) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(client);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update("UPDATE KLIENCI SET HASLO=:HASLO, IMIE=:IMIE, NAZWISKO=:NAZWISKO, EMAIL=:EMAIL, NR_TELEFONU=:NR_TELEFONU, PLEC=:PLEC,ID_SKLEPU=:ID_SKLEPU, ID_ADRESU=:ID_ADRESU WHERE ID_ADRESU=:ID_ADRESU",param);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM KLIENCI WHERE ID_KLIENTA = ?", id);
    }

}