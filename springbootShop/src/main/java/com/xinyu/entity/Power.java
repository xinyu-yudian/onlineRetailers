package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Power implements Serializable {
    private Integer id;
    private String powerName;
    private String path;
    private Integer level;
    private Integer pid;
}
