package one.digitalinnovation.bookstore.service;

import one.digitalinnovation.bookstore.model.Order;
import one.digitalinnovation.bookstore.model.Book;
import one.digitalinnovation.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        order.setOrderDate(LocalDateTime.now());
        double totalAmount = order.getBooks().stream().mapToDouble(Book::getPrice).sum();
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order update(Order order, Long id) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCustomer(order.getCustomer());
                    existingOrder.setBooks(order.getBooks());
                    existingOrder.setOrderDate(order.getOrderDate());
                    existingOrder.setTotalAmount(order.getTotalAmount());
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }
}
