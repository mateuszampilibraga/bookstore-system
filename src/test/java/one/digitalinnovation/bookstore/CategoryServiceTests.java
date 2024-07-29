package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Category;
import one.digitalinnovation.bookstore.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Fiction");

        Category savedCategory = categoryService.save(category);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals("Fiction", savedCategory.getName());
    }

    @Test
    public void testFindCategoryById() {
        Category category = new Category();
        category.setName("Science");

        Category savedCategory = categoryService.save(category);
        Category foundCategory = categoryService.findById(savedCategory.getId()).orElse(null);

        assertNotNull(foundCategory);
        assertEquals(savedCategory.getId(), foundCategory.getId());
        assertEquals("Science", foundCategory.getName());
    }
}
