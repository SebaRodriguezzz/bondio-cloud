package bondio.persistence.repository;

import bondio.persistence.entity.Bondio;
import bondio.persistence.entity.Bondio_Ingredient;
import bondio.persistence.entity.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Bondio_Order "
                        + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Bondio> bondios = order.getBondios();
        int i = 0;
        for (Bondio bondio : bondios) {
            saveBondio(orderId, i++, bondio);
        }
     return order;
    }

    private long saveBondio(Long orderId, int orderKey, Bondio bondio){
        bondio.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Bondio (name, created_at, bondio_order, bondio_order_key) values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.LONGVARCHAR, Types.LONGVARCHAR);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        bondio.getName(),
                        bondio.getCreatedAt(),
                        orderId,
                        orderKey
                ));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long bondioId = keyHolder.getKey().longValue();
        bondio.setId(bondioId);

        saveIngredientRefs(bondioId, bondio.getIngredients());

        return bondioId;
    }

    private void saveIngredientRefs(Long bondioId, List<Bondio_Ingredient> ingredients){
        int key = 0;
        for (Bondio_Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into Bondio_Ingredient (bondio, ingredient, bondio_key) values (?, ?, ?)",
                    ingredient.getIngredient(), bondioId, key++);
        }
    }
}
