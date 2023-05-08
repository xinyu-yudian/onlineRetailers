package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
     private Integer id;
     private String roleName;
     private String roleDesc;
     private Integer pid;
     private Power power;
}
