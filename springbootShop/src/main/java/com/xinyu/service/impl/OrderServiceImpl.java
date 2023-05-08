package com.xinyu.service.impl;

import com.xinyu.entity.Express;
import com.xinyu.entity.order.Order;
import com.xinyu.entity.order.OrderDetails;
import com.xinyu.mapper.express.ExpressMapper;
import com.xinyu.mapper.order.OrderDetailsMapper;
import com.xinyu.mapper.order.OrderMapper;
import com.xinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<Order> selOrders(Order order){
        return   orderMapper.selOrders(order);
    }

    @Override
    public boolean updateOrder(Order order){
        return orderMapper.updateOrder(order)>0;
    }

    @Override
    public List<OrderDetails> detailsList(OrderDetails orderDetails){
        return orderDetailsMapper.detailsList(orderDetails);
    }

    @Override
    public boolean addOrder(Order order){
        return orderMapper.addOrder(order)>0;
    }

    @Override
    public boolean addOrderDetails(OrderDetails orderDetails){
        return orderDetailsMapper.addOrderDetails(orderDetails)>0;
    }

    @Override
    public List<Express> selExpressions(String orderNumber) {
        return expressMapper.selExpressions(orderNumber);
    }
}
