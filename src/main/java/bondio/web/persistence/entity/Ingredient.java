package bondio.web.persistence.entity;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        BREAD, SEASONING, VEGGIES, CHEESE, SAUCE
    }
}
