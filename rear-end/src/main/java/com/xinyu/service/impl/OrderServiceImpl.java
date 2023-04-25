package com.xinyu.service.impl;

import com.xinyu.entity.order.Order;
import com.xinyu.mapper.order.OrderMapper;
import com.xinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> selOrders(Order order){
        return   orderMapper.selOrders(order);
    }

    @Override
    public boolean updateAddress(Order order){
        return orderMapper.updateAddress(order)>0;
    }
}
