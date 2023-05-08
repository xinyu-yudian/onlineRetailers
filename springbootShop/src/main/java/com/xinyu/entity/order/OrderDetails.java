package com.xinyu.entity.order;

import com.xinyu.entity.Goods;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetails implements Serializable {
      private Integer id;
      private Integer orderId;
      private Integer goodsId;
      private Double goodsPrice;
      private Integer goodsNumber;
      private Double goodsTotalPrice;
      private Goods goods;
}
