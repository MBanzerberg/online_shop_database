package online_shop.springapplication.shop_information_manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopInformation {
    private int ID_SKLEPU;
    private String NAZWA;
    private String DATA;
    private String ADRES_SKLEPU_WWW;
    private String ID_ADRESU;
}
