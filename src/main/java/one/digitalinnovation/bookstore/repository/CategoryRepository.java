package one.digitalinnovation.bookstore.repository;

import one.digitalinnovation.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
