package bondio.persistence.repository;

import bondio.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {

    List<Order> findByDeliveryZip(String deliveryZip);
}
