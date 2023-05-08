package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyu.entity.Express;
import com.xinyu.entity.order.Order;
import com.xinyu.entity.order.OrderDetails;
import com.xinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    private Map<String,Object> map;

    private Order order;

    private OrderDetails orderDetails;

    private static final Integer pageSize = 5;
    private Integer start = 0;
    private Integer pageNow = 1;

    private Boolean flag = false;

    @PostMapping("/getOrders")
    public String getOrders(@RequestBody() String params){
        order = new Order();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        order.setId(jsonObject.getInteger("id"));
        order.setOrderNumber(jsonObject.getString("orderNumber"));
        if (jsonObject.getInteger("payStatus") != null && !"".equals(jsonObject.getInteger("payStatus"))){
            Integer status = jsonObject.getInteger("payStatus")<0 ? null : jsonObject.getInteger("payStatus");
            order.setPayStatus(status);
        }
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
        order = new Order();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        order.setId(jsonObject.getInteger("id"));
        order.setAddress(jsonObject.getString("firstAddress")+"-"+jsonObject.getString("secondAddress"));
        pageNow = jsonObject.getInteger("pageNow");

        flag = orderService.updateOrder(order);
        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("/getOrderDetails")
    public String getOrderDetails(@RequestParam("orderId") Integer orderId){
        orderDetails = new OrderDetails();
        System.out.println("orderId:"+orderId);
        orderDetails.setOrderId(orderId);
        List<OrderDetails> detailsList = orderService.detailsList(orderDetails);
        System.out.println(detailsList);
        map = new HashMap<>();
        if(detailsList!=null && detailsList.size()>0){
            map.put("data",detailsList);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/getExpress")
    public String getExpress(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        String orderNumber = jsonObject.getString("orderNumber");
        List<Express> expressList = orderService.selExpressions(orderNumber);
        System.out.println(expressList);
        map = new HashMap<>();
        if(expressList!=null && expressList.size()>0){
            map.put("data",expressList);
        }
        return JSON.toJSONString(map);
    }

}
