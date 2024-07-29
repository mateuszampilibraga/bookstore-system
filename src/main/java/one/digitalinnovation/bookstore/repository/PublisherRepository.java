package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

