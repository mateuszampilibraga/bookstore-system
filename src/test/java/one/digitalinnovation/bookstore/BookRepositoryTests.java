package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Book;
import one.digitalinnovation.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindBook() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setIsbn("1234567890");
        book.setPrice(19.99);

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());

        Book foundBook = bookRepository.findById(savedBook.getId()).orElse(null);
        assertNotNull(foundBook);
        assertEquals(savedBook.getId(), foundBook.getId());
        assertEquals("Book Title", foundBook.getTitle());
        assertEquals("Book Author", foundBook.getAuthor());
        assertEquals("1234567890", foundBook.getIsbn());
        assertEquals(19.99, foundBook.getPrice());
    }
}
