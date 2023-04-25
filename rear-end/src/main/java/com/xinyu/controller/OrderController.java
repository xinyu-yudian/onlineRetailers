package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyu.entity.User;
import com.xinyu.entity.order.Order;
import com.xinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    private Map<String,Object> map;

    @Autowired
    private Order order;

    private static final Integer pageSize = 5;
    private Integer start = 0;
    private Integer pageNow = 1;

    private Boolean flag = false;

    @PostMapping("/getOrders")
    public String getOrders(@RequestBody() String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        order.setOrderNumber(jsonObject.getString("orderNumber"));
        pageNow = jsonObject.getInteger("pageNow");

        start = (pageNow-1)*pageSize;
        PageHelper.offsetPage(start, pageSize);
        List<Order> orderList = orderService.selOrders(order);
        PageInfo pageInfo = new PageInfo<>(orderList);
        System.out.println(pageInfo);

        long total = pageInfo.getTotal();
        long pageTotal = total%pageSize==0 ? total/pageSize : total/pageSize+1;

        map = new HashMap<String, Object>();
        if(orderList != null){
            map.put("status",200);
            map.put("data", JSON.toJSON(orderList));
            map.put("pageNow",pageNow);
            map.put("pageTotal",pageTotal);
            map.put("total",total);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateAddress")
    public String updateAddress(@RequestBody() String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        order.setId(jsonObject.getInteger("id"));
        order.setAddress(jsonObject.getString("firstAddress")+"-"+jsonObject.getString("secondAddress"));
        pageNow = jsonObject.getInteger("pageNow");

        flag = orderService.updateAddress(order);
        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }
}
