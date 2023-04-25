package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    Integer id;
    String username;
    String password;
    String email;
    String mobile;
    String roleName;
    Boolean msState;
}
