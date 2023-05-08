package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsCategory implements Serializable {
     private Integer id;
     private String categoryName;
     private Integer effective;
     private Integer level;
     private Integer pid;
     private String icon;
}
