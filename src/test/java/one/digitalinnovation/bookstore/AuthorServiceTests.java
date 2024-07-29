package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Author;
import one.digitalinnovation.bookstore.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorServiceTests {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("Author Name");
        author.setBiography("Author Biography");

        Author savedAuthor = authorService.save(author);

        assertNotNull(savedAuthor);
        assertNotNull(savedAuthor.getId());
        assertEquals("Author Name", savedAuthor.getName());
        assertEquals("Author Biography", savedAuthor.getBiography());
    }

    @Test
    public void testFindAuthorById() {
        Author author = new Author();
        author.setName("Author Name");
        author.setBiography("Author Biography");
        Author savedAuthor = authorService.save(author);

        Author foundAuthor = authorService.findById(savedAuthor.getId()).orElse(null);

        assertNotNull(foundAuthor);
        assertEquals(savedAuthor.getId(), foundAuthor.getId());
        assertEquals("Author Name", foundAuthor.getName());
        assertEquals("Author Biography", foundAuthor.getBiography());
    }
}
