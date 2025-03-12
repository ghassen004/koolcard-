package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.OrderEntity;
import com.example.koolcard.entities.OrderItem;
import com.example.koolcard.repository.OrderRepository;
import com.example.koolcard.repository.PlatRepository;
import com.example.koolcard.services.OrderInterface;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService implements OrderInterface {

    private final OrderRepository orderRepository;
    private final PlatRepository platRepository;

    public OrderService(OrderRepository orderRepository, PlatRepository platRepository) {
        this.orderRepository = orderRepository;
        this.platRepository = platRepository;
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
                item.setUnitPrice(platRepository.findById(item.getPlat().getId())
                        .orElseThrow(() -> new RuntimeException("Plat not found")).getPrice());
            }
        }

        double total = order.getOrderItems().stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public OrderEntity updateOrder(Long id, OrderEntity updatedOrder) {
        OrderEntity existingOrder = getOrderById(id);
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with ID: " + id);
        }
    }
}
