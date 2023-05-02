package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.Cart;
import com.xinyu.entity.User;
import com.xinyu.service.UserService;
import com.xinyu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class LogRegController {
    @Autowired
    private UserService userService;
    @Autowired
    private User user;
    private Map<String, Object> map;
    private List<Cart> cartList = new ArrayList<>();
    private ServletContext application;

    @RequestMapping( "/login")
    public String login(@RequestBody String param, HttpServletResponse resp,HttpServletRequest req){
        application = req.getServletContext();
        map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        user = userService.login(username,password);

        if(user!=null){
             String token = JWTUtils.getToken(user.getId(),user.getUsername(),user.getPassword());
             resp.setHeader("token",token);
             application.setAttribute("user",user);
             application.setAttribute("cartList",cartList);
             System.out.println("创建购物车：" +application.getAttribute("cartList"));
             map.put("status",200);
             map.put("data", JSON.toJSON(user));
             map.put("token", token);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }
}
