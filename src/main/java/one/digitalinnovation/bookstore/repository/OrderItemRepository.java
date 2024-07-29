package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
