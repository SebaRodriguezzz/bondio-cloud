package bondio.persistence.entity.udt;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("bondio")
public class BondioUDT {

    private final String name;
    private final List<IngredientUDT> ingredients;
}
