package bondio.web;

import bondio.enums.IngredientType;
import bondio.persistence.entity.Bondio;
import bondio.persistence.entity.Ingredient;
import bondio.persistence.entity.Order;
import bondio.persistence.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBondioController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignBondioController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
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
    public String processBondio(@Valid Bondio bondio,
                                Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing bondio: {}", bondio);
        order.addBondio(bondio);
        return "redirect:/orders/current";
    }
}
