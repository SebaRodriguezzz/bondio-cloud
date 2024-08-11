package bondio.persistence.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Data
@Table
public class Bondio {

    @Id
    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
