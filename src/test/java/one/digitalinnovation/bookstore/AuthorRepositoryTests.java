package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Author;
import one.digitalinnovation.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testSaveAndFindAuthor() {
        Author author = new Author();
        author.setName("Author Name");
        author.setBiography("Author Biography");

        Author savedAuthor = authorRepository.save(author);

        assertNotNull(savedAuthor);
        assertNotNull(savedAuthor.getId());

        Author foundAuthor = authorRepository.findById(savedAuthor.getId()).orElse(null);
        assertNotNull(foundAuthor);
        assertEquals(savedAuthor.getId(), foundAuthor.getId());
        assertEquals("Author Name", foundAuthor.getName());
        assertEquals("Author Biography", foundAuthor.getBiography());
    }
}
