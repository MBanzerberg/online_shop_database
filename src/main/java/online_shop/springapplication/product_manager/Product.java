package online_shop.springapplication.product_manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

     private int ID_PRODUKTU;
     private String NAZWA;
     private String CENA;
     private String SPECYFIKACJA;
     private String OPIS;
     private String ID_SKLEPU;
}