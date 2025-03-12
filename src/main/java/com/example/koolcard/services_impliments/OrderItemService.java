package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.OrderItem;
import com.example.koolcard.repository.OrderItemRepository;
import com.example.koolcard.services.OrderItemInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements OrderItemInterface {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found with ID: " + id));
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem existingItem = getOrderItemById(id);
        existingItem.setQuantity(updatedOrderItem.getQuantity());
        existingItem.setUnitPrice(updatedOrderItem.getUnitPrice());
        return orderItemRepository.save(existingItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order Item not found with ID: " + id);
        }
    }
}
