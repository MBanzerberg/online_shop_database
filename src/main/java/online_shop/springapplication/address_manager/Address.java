package online_shop.springapplication.address_manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private int ID_ADRESU;
    private String MIASTO;
    private String KOD_POCZTOWY;
    private String ULICA;
    private String NUMER_DOMU;
    private String NUMER_MIESZKANIA;
}

