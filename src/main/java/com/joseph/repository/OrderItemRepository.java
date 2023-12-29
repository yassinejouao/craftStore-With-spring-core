package com.joseph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseph.entity.Order;
import com.joseph.entity.OrderItem;
import java.util.List;

@Repository("orderItemRepository")
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    void deleteByOrder(Order order);

}
