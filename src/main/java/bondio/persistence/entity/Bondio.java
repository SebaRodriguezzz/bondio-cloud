package bondio.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class Bondio {

    private String name;
    private List<Ingredient> ingredients;

}
