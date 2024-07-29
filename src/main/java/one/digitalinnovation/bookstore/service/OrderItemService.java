package one.digitalinnovation.bookstore.service;

import one.digitalinnovation.bookstore.model.OrderItem;
import one.digitalinnovation.bookstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem save(OrderItem orderItem) {
        orderItem.setPrice(orderItem.getBook().getPrice() * orderItem.getQuantity());
        return orderItemRepository.save(orderItem);
    }

    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    public OrderItem update(OrderItem orderItem, Long id) {
        return orderItemRepository.findById(id)
                .map(existingOrderItem -> {
                    existingOrderItem.setOrder(orderItem.getOrder());
                    existingOrderItem.setBook(orderItem.getBook());
                    existingOrderItem.setQuantity(orderItem.getQuantity());
                    existingOrderItem.setPrice(orderItem.getBook().getPrice() * orderItem.getQuantity());
                    return orderItemRepository.save(existingOrderItem);
                })
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id " + id));
    }
}
