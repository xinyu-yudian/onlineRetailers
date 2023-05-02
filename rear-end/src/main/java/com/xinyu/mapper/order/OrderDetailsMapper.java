package com.xinyu.mapper.order;

import com.xinyu.entity.order.OrderDetails;

import java.util.List;

public interface OrderDetailsMapper {
    List<OrderDetails> detailsList(OrderDetails orderDetails);

    int addOrderDetails(OrderDetails orderDetails);
}
