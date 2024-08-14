package bondio.persistence.repository;

import bondio.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {}
