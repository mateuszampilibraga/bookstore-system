package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
