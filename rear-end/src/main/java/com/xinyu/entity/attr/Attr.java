package com.xinyu.entity.attr;

import lombok.Data;

import java.io.Serializable;

@Data
public class Attr implements Serializable {
   private Integer id;
   private String attrName;
   private Integer cid;
}
