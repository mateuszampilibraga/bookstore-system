package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
