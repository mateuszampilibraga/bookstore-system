package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
