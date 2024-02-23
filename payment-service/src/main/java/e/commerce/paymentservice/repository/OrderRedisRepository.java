package e.commerce.paymentservice.repository;

import e.commerce.paymentservice.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedisRepository extends CrudRepository<OrderEntity, String> {
}
