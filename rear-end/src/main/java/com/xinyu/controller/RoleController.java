package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.Role;
import com.xinyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private Role role;

    private Map<String, Object> map;

    private Boolean flag = false;

    @PostMapping("/getRoleAndPower")
    public String getRoleAndPower() {
        String json = roleService.selRoleAndPower();
        return json;
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        role.setRoleName(jsonObject.getString("roleName"));
        role.setRoleDesc(jsonObject.getString("roleDesc"));
        flag = roleService.addRole(role);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        role.setId(jsonObject.getInteger("id"));
        role.setRoleName(jsonObject.getString("roleName"));
        role.setRoleDesc(jsonObject.getString("roleDesc"));
        flag = roleService.updateRole(role);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeRole/{id}")
    public String removeRole(@PathVariable Integer id) {
        flag = roleService.removeRole(id);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }
}
