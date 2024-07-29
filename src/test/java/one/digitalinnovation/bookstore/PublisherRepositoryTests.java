package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Publisher;
import one.digitalinnovation.bookstore.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PublisherRepositoryTests {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void testSaveAndFindPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Springer");

        Publisher savedPublisher = publisherRepository.save(publisher);

        assertNotNull(savedPublisher);
        assertNotNull(savedPublisher.getId());

        Publisher foundPublisher = publisherRepository.findById(savedPublisher.getId()).orElse(null);
        assertNotNull(foundPublisher);
        assertEquals(savedPublisher.getId(), foundPublisher.getId());
        assertEquals("Springer", foundPublisher.getName());
    }
}
