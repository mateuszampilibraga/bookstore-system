package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Category;
import one.digitalinnovation.bookstore.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveAndFindCategory() {
        Category category = new Category();
        category.setName("Fantasy");

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());

        Category foundCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);
        assertNotNull(foundCategory);
        assertEquals(savedCategory.getId(), foundCategory.getId());
        assertEquals("Fantasy", foundCategory.getName());
    }
}
