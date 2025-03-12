package com.example.koolcard.services;

import com.example.koolcard.entities.OrderEntity;
import java.util.List;

public interface OrderInterface {
    OrderEntity createOrder(OrderEntity order);
    List<OrderEntity> getAllOrders();
    OrderEntity getOrderById(Long id);
    OrderEntity updateOrder(Long id, OrderEntity updatedOrder);
    void deleteOrder(Long id);
}
