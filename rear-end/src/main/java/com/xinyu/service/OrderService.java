package com.xinyu.service;

import com.xinyu.entity.Express;
import com.xinyu.entity.order.Order;
import com.xinyu.entity.order.OrderDetails;

import java.util.List;

public interface OrderService {
    List<Order> selOrders(Order order);

    boolean updateOrder(Order order);

    List<OrderDetails> detailsList(OrderDetails orderDetails);

    boolean addOrder(Order order);
    boolean addOrderDetails(OrderDetails orderDetails);
    List<Express> selExpressions(String orderNumber);
}
