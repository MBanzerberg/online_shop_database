package online_shop.springapplication.client_information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private int ID_KLIENTA;
    private String HASLO;
    private String IMIE;
    private String NAZWISKO;
    private String EMAIL;
    private String NR_TELEFONU;
    private String PLEC;
    private String ID_SKLEPU;
    private String ID_ADRESU;
}
