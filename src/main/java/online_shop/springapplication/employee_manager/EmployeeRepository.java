package online_shop.springapplication.employee_manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM PRACOWNICY", BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public void save(Employee employee) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("PRACOWNICY").usingColumns("IMIE","DRUGIE_IMIE","NAZWISKO","DATA_URODZENIA","PLEC","PESEL","NR_TELEFONU","EMAIL","WYKSZTALCENIE","DATA_ZATRUDNIENIA","STANOWISKO","WYNAGRODZENIE","ID_SKLEPU","ID_PLACOWKI","ID_ADRESU");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        insertActor.execute(param);
    }

    public Employee get(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM PRACOWNICY WHERE ID_PRACOWNIKA = ?",args,BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public void update(Employee employee) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update("UPDATE PRACOWNICY SET IMIE=:IMIE, DRUGIE_IMIE=:DRUGIE_IMIE," +
                " NAZWISKO=:NAZWISKO, PLEC=:PLEC, PESEL=:PESEL, NR_TELEFONU=:NR_TELEFONU, EMAIL=:EMAIL, WYKSZTALCENIE=:WYKSZTALCENIE, " +
                "DATA_ZATRUDNIENIA=:DATA_ZATRUDNIENIA, ID_SKLEPU=:ID_SKLEPU, ID_PLACOWKI=ID_PLACOWKI, ID_ADRESU=:ID_ADRESU WHERE ID_PRACOWNIKA=:ID_PRACOWNIKA",param);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PRACOWNICY WHERE ID_PRACOWNIKA = ?", id);
    }

}
