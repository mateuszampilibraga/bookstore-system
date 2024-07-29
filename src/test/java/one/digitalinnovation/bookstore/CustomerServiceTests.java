package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Customer;
import one.digitalinnovation.bookstore.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("Alice Smith");
        customer.setEmail("alice.smith@example.com");
        customer.setAddress("789 Pine St");

        Customer savedCustomer = customerService.save(customer);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertEquals("Alice Smith", savedCustomer.getName());
        assertEquals("alice.smith@example.com", savedCustomer.getEmail());
        assertEquals("789 Pine St", savedCustomer.getAddress());
    }

    @Test
    public void testFindCustomerById() {
        Customer customer = new Customer();
        customer.setName("Bob Brown");
        customer.setEmail("bob.brown@example.com");
        customer.setAddress("101 Maple St");

        Customer savedCustomer = customerService.save(customer);
        Customer foundCustomer = customerService.findById(savedCustomer.getId()).orElse(null);

        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getId(), foundCustomer.getId());
        assertEquals("Bob Brown", foundCustomer.getName());
        assertEquals("bob.brown@example.com", foundCustomer.getEmail());
        assertEquals("101 Maple St", foundCustomer.getAddress());
    }
}
