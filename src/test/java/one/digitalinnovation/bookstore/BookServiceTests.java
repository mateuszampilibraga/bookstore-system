package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Book;
import one.digitalinnovation.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setIsbn("1234567890");
        book.setPrice(19.99);

        Book savedBook = bookService.save(book);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
        assertEquals("Book Title", savedBook.getTitle());
        assertEquals("Book Author", savedBook.getAuthor());
        assertEquals("1234567890", savedBook.getIsbn());
        assertEquals(19.99, savedBook.getPrice());
    }

    @Test
    public void testFindBookById() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setIsbn("1234567890");
        book.setPrice(19.99);
        Book savedBook = bookService.save(book);

        Book foundBook = bookService.findById(savedBook.getId()).orElse(null);

        assertNotNull(foundBook);
        assertEquals(savedBook.getId(), foundBook.getId());
        assertEquals("Book Title", foundBook.getTitle());
        assertEquals("Book Author", foundBook.getAuthor());
        assertEquals("1234567890", foundBook.getIsbn());
        assertEquals(19.99, foundBook.getPrice());
    }
}
