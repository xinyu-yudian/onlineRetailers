package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.attr.Attr;
import com.xinyu.entity.attr.AttrChild;
import com.xinyu.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AttrController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private Attr attr;

    @Autowired
    private AttrChild attrChild;

    private Boolean flag = false;

    private Map<String,Object> map;

    @GetMapping("/getAttrs/{cid}")
    public String getAttrs(@PathVariable Integer cid){
        String json = attrService.attrJson(cid);
        System.out.println(json);
        return json;
    }

    @PostMapping("/addAttr")
    public String addAttr(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        attr.setAttrName(jsonObject.getString("attrName"));
        attr.setCid(jsonObject.getInteger("cid"));
        flag = attrService.addAttr(attr);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateAttr")
    public String updateAttr(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        attr.setId(jsonObject.getInteger("id"));
        attr.setAttrName(jsonObject.getString("attrName"));
        flag = attrService.updateAttr(attr);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeAttr/{id}")
    public String removeAttr(@PathVariable Integer id){
        flag = attrService.removeAttr(id);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addAttrChild")
    public String addAttrChild(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        attrChild.setChildName(jsonObject.getString("childName"));
        attrChild.setAid(jsonObject.getInteger("aid"));
        flag = attrService.addAttrChild(attrChild);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateAttrChild")
    public String updateAttrChild(@RequestBody String params){
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        attrChild.setId(jsonObject.getInteger("id"));
        attrChild.setChildName(jsonObject.getString("childName"));
        flag = attrService.updateAttrChild(attrChild);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeAttrChild/{id}")
    public String removeAttrChild(@PathVariable Integer id){
        flag = attrService.removeAttrChild(id);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }



}
