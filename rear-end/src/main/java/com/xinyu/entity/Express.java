package com.xinyu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Express implements Serializable {
    Integer id;
    String orderNumber;
    String time;
    String finishTime;
    String context;
    String location;
}
