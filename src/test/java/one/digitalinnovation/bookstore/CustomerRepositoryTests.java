package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Customer;
import one.digitalinnovation.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveAndFindCustomer() {
        Customer customer = new Customer();
        customer.setName("Charlie Davis");
        customer.setEmail("charlie.davis@example.com");
        customer.setAddress("202 Birch St");

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());

        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getId(), foundCustomer.getId());
        assertEquals("Charlie Davis", foundCustomer.getName());
        assertEquals("charlie.davis@example.com", foundCustomer.getEmail());
        assertEquals("202 Birch St", foundCustomer.getAddress());
    }
}
