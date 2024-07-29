package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Publisher;
import one.digitalinnovation.bookstore.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PublisherServiceTests {

    @Autowired
    private PublisherService publisherService;

    @Test
    public void testCreatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("O'Reilly Media");

        Publisher savedPublisher = publisherService.save(publisher);

        assertNotNull(savedPublisher);
        assertNotNull(savedPublisher.getId());
        assertEquals("O'Reilly Media", savedPublisher.getName());
    }

    @Test
    public void testFindPublisherById() {
        Publisher publisher = new Publisher();
        publisher.setName("Manning Publications");

        Publisher savedPublisher = publisherService.save(publisher);
        Publisher foundPublisher = publisherService.findById(savedPublisher.getId()).orElse(null);

        assertNotNull(foundPublisher);
        assertEquals(savedPublisher.getId(), foundPublisher.getId());
        assertEquals("Manning Publications", foundPublisher.getName());
    }
}
