package com.xinyu.entity.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamChild implements Serializable {
    private Integer id;
    private String childName;
    private Integer pid;
}
