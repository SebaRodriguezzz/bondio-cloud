package bondio.persistence.repository;

import bondio.persistence.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
