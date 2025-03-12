package com.example.koolcard.services;

import com.example.koolcard.entities.OrderItem;
import java.util.List;

public interface OrderItemInterface {
    OrderItem addOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(Long id);
    OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem);
    void deleteOrderItem(Long id);
}
