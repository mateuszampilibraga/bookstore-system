package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Book;
import one.digitalinnovation.bookstore.model.Customer;
import one.digitalinnovation.bookstore.model.Order;
import one.digitalinnovation.bookstore.repository.BookRepository;
import one.digitalinnovation.bookstore.repository.CustomerRepository;
import one.digitalinnovation.bookstore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    private Customer customer;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        // Criar e salvar um Customer
        customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Elm Street");
        customerRepository.save(customer);

        // Criar e salvar dois Books
        book1 = new Book();
        book1.setTitle("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setIsbn("978-0134685991");
        book1.setPrice(45.00);
        bookRepository.save(book1);

        book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setIsbn("978-0132350884");
        book2.setPrice(40.00);
        bookRepository.save(book2);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setCustomer(customer);
        order.setBooks(Arrays.asList(book1, book2));
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(book1.getPrice() + book2.getPrice());

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertEquals(customer.getId(), savedOrder.getCustomer().getId());
        assertEquals(2, savedOrder.getBooks().size());
        assertEquals(book1.getPrice() + book2.getPrice(), savedOrder.getTotalAmount());
    }

    @Test
    public void testFindOrderById() {
        Order order = new Order();
        order.setCustomer(customer);
        order.setBooks(Arrays.asList(book1, book2));
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(book1.getPrice() + book2.getPrice());
        Order savedOrder = orderRepository.save(order);

        Order foundOrder = orderRepository.findById(savedOrder.getId()).orElse(null);

        assertNotNull(foundOrder);
        assertEquals(savedOrder.getId(), foundOrder.getId());
        assertEquals(customer.getId(), foundOrder.getCustomer().getId());
        assertEquals(2, foundOrder.getBooks().size());
        assertEquals(book1.getPrice() + book2.getPrice(), foundOrder.getTotalAmount());
    }

    @Test
    public void testUpdateOrder() {
        // Criar e salvar dois novos livros
        Book book3 = new Book();
        book3.setTitle("Design Patterns");
        book3.setAuthor("Erich Gamma");
        book3.setIsbn("978-0201633610");
        book3.setPrice(50.00);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Refactoring");
        book4.setAuthor("Martin Fowler");
        book4.setIsbn("978-0201485677");
        book4.setPrice(55.00);
        bookRepository.save(book4);

        // Criar e salvar um pedido com dois livros
        Order order = new Order();
        order.setCustomer(customer);
        order.setBooks(Arrays.asList(book1, book2));
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(book1.getPrice() + book2.getPrice());
        Order savedOrder = orderRepository.save(order);

        // Adicionar novos livros ao pedido
        List<Book> updatedBooks = new ArrayList<>(savedOrder.getBooks()); // Manter livros existentes
        updatedBooks.add(book3);
        updatedBooks.add(book4);

        // Atualizar o pedido
        savedOrder.setBooks(updatedBooks); // Adicionar novos livros
        double newTotalAmount = updatedBooks.stream().mapToDouble(Book::getPrice).sum(); // Calcular novo total
        savedOrder.setTotalAmount(newTotalAmount);

        Order updatedOrder = orderRepository.save(savedOrder);

        // Verificar se a atualização foi aplicada corretamente
        assertNotNull(updatedOrder);
        assertEquals(4, updatedOrder.getBooks().size()); // Verificar que agora há 4 livros
        assertTrue(updatedOrder.getBooks().containsAll(Arrays.asList(book1, book2, book3, book4)));
        assertEquals(newTotalAmount, updatedOrder.getTotalAmount(), 0.01); // Comparar valores com tolerância
    }



    @Test
    public void testDeleteOrder() {
        Order order = new Order();
        order.setCustomer(customer);
        order.setBooks(Arrays.asList(book1, book2));
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(book1.getPrice() + book2.getPrice());
        Order savedOrder = orderRepository.save(order);

        orderRepository.deleteById(savedOrder.getId());
        Order deletedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);

        assertNull(deletedOrder);
    }
}
