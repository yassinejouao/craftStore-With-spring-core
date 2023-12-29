package com.joseph.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseph.Model.rapportData;
import com.joseph.entity.Order;
import com.joseph.repository.OrderRepository;
import com.joseph.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Order with ID = ${id} is not found"));

    }

    @Override
    @Transactional
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOrder(Order Order) {
        orderRepository.save(Order);
    }

    @Override
    public List<rapportData> getTotalOrdersBetweenDates(LocalDate starDate, LocalDate endDate) {
        return orderRepository.getTotalOrdersBetweenDates(starDate, endDate);
    }

}
