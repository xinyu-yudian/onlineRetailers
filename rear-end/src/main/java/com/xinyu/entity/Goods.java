package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {
       private Integer id;
       private String goodsName;
       private Double price;
       private Integer number;
       private Double weight;
       private String addTime;
       private String icon;
       private Integer catId;
       private String attrs;
       private String params;
       private String introduce;
}
