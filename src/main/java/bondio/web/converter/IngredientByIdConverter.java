package bondio.web.converter;

import bondio.enums.IngredientType;
import bondio.persistence.entity.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("CBTA", new Ingredient("CBTA", "Ciabatta", IngredientType.BREAD));
        ingredientMap.put("WHTB", new Ingredient("WHTB", "White Bread", IngredientType.BREAD));
        ingredientMap.put("SALT", new Ingredient("SALT", "Salt", IngredientType.SEASONING));
        ingredientMap.put("PEPP", new Ingredient("PEPP", "Pepper", IngredientType.SEASONING));
        ingredientMap.put("TOMA", new Ingredient("TOMA", "Tomato", IngredientType.VEGGIES));
        ingredientMap.put("ONIO", new Ingredient("ONIO", "Onion", IngredientType.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
        ingredientMap.put("PROV", new Ingredient("PROV", "Provolone", IngredientType.CHEESE));
        ingredientMap.put("YLLW", new Ingredient("YLLW", "Yellow Mustard", IngredientType.SAUCE));
        ingredientMap.put("MAYO", new Ingredient("MAYO", "Mayonnaise", IngredientType.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
