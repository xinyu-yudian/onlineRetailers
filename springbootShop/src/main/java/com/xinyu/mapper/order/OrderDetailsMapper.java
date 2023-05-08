package com.xinyu.mapper.order;

import com.xinyu.entity.order.OrderDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailsMapper {
    List<OrderDetails> detailsList(OrderDetails orderDetails);

    int addOrderDetails(OrderDetails orderDetails);
}
