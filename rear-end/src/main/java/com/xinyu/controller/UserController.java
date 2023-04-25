package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyu.entity.User;
import com.xinyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private User user;
    private Map<String, Object> map;
    private static final Integer pageSize = 5;
    private Integer start = 0;
    private Integer pageNow = 1;

    @PostMapping("/getUsers")
    public String getUsers(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        user.setId(jsonObject.getInteger("id"));
        user.setUsername(jsonObject.getString("username"));
        user.setRoleName(jsonObject.getString("roleName"));
        pageNow = jsonObject.getInteger("pageNow");

        start = (pageNow-1)*pageSize;
        PageHelper.offsetPage(start, pageSize);
        List<User> userList = userService.getUserList(user);
        PageInfo pageInfo = new PageInfo<>(userList);
        System.out.println(pageInfo);

        long total = pageInfo.getTotal();
        long pageTotal = total%pageSize==0 ? total/pageSize : total/pageSize+1;

        map = new HashMap<String, Object>();
        if(userList != null){
            map.put("status",200);
            map.put("data", JSON.toJSON(userList));
            map.put("pageNow",pageNow);
            map.put("pageTotal",pageTotal);
            map.put("total",total);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        user.setUsername(jsonObject.getString("username"));
        user.setPassword(jsonObject.getString("password"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobile(jsonObject.getString("mobile"));
        boolean flag = userService.addUser(user);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        user.setId(jsonObject.getInteger("id"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobile(jsonObject.getString("mobile"));
        boolean flag = userService.updateUser(user);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeUser/{id}")
    public String removeUser(@PathVariable Integer id) {
        boolean flag = userService.removeUser(id);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PutMapping("updateUserState/{id}/{state}")
    public String updateUserState(@PathVariable Integer id,@PathVariable Boolean state) {
        boolean flag = userService.updateUserState(id,state);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }
}
