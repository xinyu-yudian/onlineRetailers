package com.xinyu.entity.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class Param implements Serializable {
   private Integer id;
   private String paramName;
   private Integer cid;
}
