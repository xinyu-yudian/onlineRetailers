package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Report implements Serializable {
      private Integer id;
      private Integer userCount;
      private String area;
      private String date;
}
