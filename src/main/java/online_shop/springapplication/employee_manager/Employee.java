package online_shop.springapplication.employee_manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int ID_PRACOWNIKA;
    private String IMIE;
    private String DRUGIE_IMIE;
    private String NAZWISKO;
    private String DATA_URODZENIA;
    private String PLEC;
    private String PESEL;
    private String NR_TELEFONU;
    private String EMAIL;
    private String WYKSZTALCENIE;
    private String DATA_ZATRUDNIENIA;
    private String STANOWISKO;
    private String WYNAGRODZENIE;
    private String ID_SKLEPU;
    private String ID_PLACOWKI;
    private String ID_ADRESU;

}

