package com.joseph.service;

import java.time.LocalDate;
import java.util.List;

import com.joseph.Model.rapportData;
import com.joseph.entity.Order;

public interface OrderService {
    public List<Order> getOrders();

    public void saveOrder(Order Order);

    public Order getOrder(Long id);

    public void deleteOrder(Long id);

    public List<rapportData> getTotalOrdersBetweenDates(LocalDate starDate, LocalDate endDate);
}
