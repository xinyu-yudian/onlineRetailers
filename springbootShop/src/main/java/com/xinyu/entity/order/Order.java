package com.xinyu.entity.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
      private Integer id;
      private String orderNumber;
      private Integer userId;
      private Integer payStatus;
      private String isSend;
      private String organ;
      private String company;
      private String invoice;
      private String address;
      private String placeTime;
      private Double orderPrice;
}
