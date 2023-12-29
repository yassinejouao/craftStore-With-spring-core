package com.joseph.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseph.entity.Order;
import com.joseph.entity.OrderItem;
import com.joseph.repository.OrderItemRepository;
import com.joseph.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    @Override
    public void saveOrderItem(OrderItem OrderItem) {
        orderItemRepository.save(OrderItem);
    }

    @Override
    public void deleteOrderItems(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public void deleteByOrder(Order order) {
        orderItemRepository.deleteByOrder(order);
    }

}
