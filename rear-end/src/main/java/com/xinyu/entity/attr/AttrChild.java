package com.xinyu.entity.attr;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrChild implements Serializable {
   private Integer id;
   private String childName;
   private Integer aid;
}
