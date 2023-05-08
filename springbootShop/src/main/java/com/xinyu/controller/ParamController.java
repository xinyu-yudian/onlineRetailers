package com.xinyu.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.param.Param;
import com.xinyu.entity.param.ParamChild;
import com.xinyu.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ParamController {

    @Autowired
    private ParamService paramService;

    private Param param;

    private ParamChild paramChild;

    private Boolean flag = false;

    private Map<String,Object> map;

    @GetMapping("/getParams/{cid}")
    public String getAttrs(@PathVariable Integer cid){
        String json = paramService.paramJson(cid);
        System.out.println(json);
        return json;
    }

    @PostMapping("/addParam")
    public String addParam(@RequestBody String params){
        param = new Param();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        param.setParamName(jsonObject.getString("paramName"));
        param.setCid(jsonObject.getInteger("cid"));
        flag = paramService.addParam(param);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateParam")
    public String updateParam(@RequestBody String params){
        param = new Param();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        param.setId(jsonObject.getInteger("id"));
        param.setParamName(jsonObject.getString("paramName"));
        flag = paramService.updateParam(param);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeParam/{id}")
    public String removeParam(@PathVariable Integer id){
        flag = paramService.removeParam(id);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addParamChild")
    public String addParamChild(@RequestBody String params){
        paramChild = new ParamChild();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        paramChild.setChildName(jsonObject.getString("childName"));
        paramChild.setPid(jsonObject.getInteger("pid"));
        flag = paramService.addParamChild(paramChild);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateParamChild")
    public String updateParamChild(@RequestBody String params){
        paramChild = new ParamChild();
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        paramChild.setId(jsonObject.getInteger("id"));
        paramChild.setChildName(jsonObject.getString("childName"));
        flag = paramService.updateParamChild(paramChild);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeParamChild/{id}")
    public String removeParamChild(@PathVariable Integer id){
        flag = paramService.removeParamChild(id);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

}
