package bondio.web;

import bondio.enums.IngredientType;
import bondio.persistence.entity.Bondio;
import bondio.persistence.entity.Ingredient;
import bondio.persistence.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("bondioOrder")
public class DesignBondioController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CBTA", "Ciabatta", IngredientType.BREAD),
                new Ingredient("WHTB", "White Bread", IngredientType.BREAD),
                new Ingredient("SALT", "Salt", IngredientType.SEASONING),
                new Ingredient("PEPP", "Pepper", IngredientType.SEASONING),
                new Ingredient("TOMA", "Tomato", IngredientType.VEGGIES),
                new Ingredient("ONIO", "Onion", IngredientType.VEGGIES),
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                new Ingredient("PROV", "Provolone", IngredientType.CHEESE),
                new Ingredient("YLLW", "Yellow Mustard", IngredientType.SAUCE),
                new Ingredient("MAYO", "Mayonnaise", IngredientType.SAUCE)
        );

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order () {
        return new Order();
    }

    @ModelAttribute(name = "bondio")
    public Bondio bondio () {
        return new Bondio();
    }

    @GetMapping
    public String showDesignForm () {
        return "design";
    }

    private Iterable<Ingredient> filterByType (
            List<Ingredient> ingredients, IngredientType type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processBondio(Bondio bondio, @ModelAttribute Order order) {
        log.info("Processing bondio: " + bondio);
        order.addBondio(bondio);
        return "redirect:/orders/current";
    }
}
