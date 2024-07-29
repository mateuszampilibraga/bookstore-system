package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.*;
import one.digitalinnovation.bookstore.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class BookstoreApplicationTests {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@BeforeEach
	void setUp() {
		authorRepository.deleteAll();
		bookRepository.deleteAll();
		categoryRepository.deleteAll();
		customerRepository.deleteAll();
		orderRepository.deleteAll();
		publisherRepository.deleteAll();
		orderItemRepository.deleteAll();
	}

	@Test
	void testCrudOperations() {
		// Create and save entities
		Author author = new Author();
		author.setName("George Orwell");
		author.setBiography("English novelist and essayist.");
		Author savedAuthor = authorRepository.save(author);

		Book book = new Book();
		book.setTitle("1984");
		book.setAuthor("George Orwell");
		book.setIsbn("978-0451524935");
		book.setPrice(20.00);
		Book savedBook = bookRepository.save(book);

		Category category = new Category();
		category.setName("Dystopian");
		Category savedCategory = categoryRepository.save(category);

		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setEmail("john.doe@example.com");
		customer.setAddress("123 Main St, Anytown, USA");
		Customer savedCustomer = customerRepository.save(customer);

		Publisher publisher = new Publisher();
		publisher.setName("Secker & Warburg");
		Publisher savedPublisher = publisherRepository.save(publisher);

		// Create and save an Order
		Order order = new Order();
		order.setCustomer(savedCustomer);
		order.setBooks(Arrays.asList(savedBook));
		order.setOrderDate(LocalDateTime.now());
		order.setTotalAmount(savedBook.getPrice());
		Order savedOrder = orderRepository.save(order);

		// Test retrieval and updating
		assertNotNull(savedOrder.getId());

		Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);
		assertNotNull(retrievedOrder);
		assertEquals(1, retrievedOrder.getBooks().size());

		// Add more books to the order
		Book book2 = new Book();
		book2.setTitle("Animal Farm");
		book2.setAuthor("George Orwell");
		book2.setIsbn("978-0451526342");
		book2.setPrice(15.00);
		Book savedBook2 = bookRepository.save(book2);

		retrievedOrder.getBooks().add(savedBook2);
		retrievedOrder.setTotalAmount(retrievedOrder.getBooks().stream().mapToDouble(Book::getPrice).sum());
		Order updatedOrder = orderRepository.save(retrievedOrder);

		assertEquals(2, updatedOrder.getBooks().size());
		assertEquals(35.00, updatedOrder.getTotalAmount());

		// Cleanup
		orderRepository.deleteAll();
		bookRepository.deleteAll();
		authorRepository.deleteAll();
		categoryRepository.deleteAll();
		customerRepository.deleteAll();
		publisherRepository.deleteAll();
	}
}
