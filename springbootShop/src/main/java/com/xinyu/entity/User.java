package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
   private Integer id;
   private String username;
   private String password;
   private String email;
   private String mobile;
   private String roleName;
   private Boolean msState;
}
