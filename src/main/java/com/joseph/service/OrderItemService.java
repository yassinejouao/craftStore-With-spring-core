package com.joseph.service;

import java.util.List;

import com.joseph.entity.Order;
import com.joseph.entity.OrderItem;

public interface OrderItemService {
    public List<OrderItem> getOrderItemsByOrderId(Order order);

    public void saveOrderItem(OrderItem OrderItem);

    public void deleteOrderItems(Long id);

    public void deleteByOrder(Order order);

}
