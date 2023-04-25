package com.xinyu.mapper.order;

import com.xinyu.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> selOrders(Order order);
    int updateAddress(Order order);
}
