package com.xinyu.service;

import com.xinyu.entity.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> selOrders(Order order);

    boolean updateAddress(Order order);
}
