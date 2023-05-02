package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable {
    private Integer number;
    private Goods goods;
    private Boolean checked;
}
