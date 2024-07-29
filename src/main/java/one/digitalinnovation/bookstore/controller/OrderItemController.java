package one.digitalinnovation.bookstore.controller;

import one.digitalinnovation.bookstore.model.OrderItem;
import one.digitalinnovation.bookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.findById(id);
        if (orderItem.isPresent()) {
            return ResponseEntity.ok(orderItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long id) {
        Optional<OrderItem> updatedOrderItem = Optional.ofNullable(orderItemService.update(orderItem, id));
        if (updatedOrderItem.isPresent()) {
            return ResponseEntity.ok(updatedOrderItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        if (orderItemService.findById(id).isPresent()) {
            orderItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
