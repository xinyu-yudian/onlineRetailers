package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.User;
import com.xinyu.service.UserService;
import com.xinyu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
public class LogRegController {
    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    private Map<String, Object> map;

    @RequestMapping( "/login")
    public String login(@RequestBody String param){
        map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        user = userService.login(username,password);

        if(user!=null){
            String token = JWTUtils.getToken(user.getId(),user.getUsername(),user.getPassword());
            map.put("status",200);
            map.put("data", JSON.toJSON(user));
            map.put("token", token);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }
}
