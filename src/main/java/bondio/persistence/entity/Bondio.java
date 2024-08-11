package bondio.persistence.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Bondio {

    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
