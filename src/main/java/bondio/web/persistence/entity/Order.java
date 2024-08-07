package bondio.web.persistence.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Bondio> bondios = new ArrayList<>();
    public void addBondio(Bondio bondio) {
        this.bondios.add(bondio);
    }
}
