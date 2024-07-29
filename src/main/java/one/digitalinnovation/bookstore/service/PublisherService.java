package one.digitalinnovation.bookstore.service;

import one.digitalinnovation.bookstore.model.Publisher;
import one.digitalinnovation.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }

    public Publisher update(Publisher publisher, Long id) {
        return publisherRepository.findById(id)
                .map(existingPublisher -> {
                    existingPublisher.setName(publisher.getName());
                    return publisherRepository.save(existingPublisher);
                })
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
    }
}
