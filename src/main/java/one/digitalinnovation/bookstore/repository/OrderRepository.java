package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
