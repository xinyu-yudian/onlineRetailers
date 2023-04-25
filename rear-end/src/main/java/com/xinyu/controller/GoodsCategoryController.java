package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinyu.entity.GoodsCategory;
import com.xinyu.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private GoodsCategory goodsCategory;

    private Map<String,Object> map;

    private Boolean flag = false;

    @PostMapping("/treeGoodsCategory")
    public String treeGoodsCategory(){
        String json = goodsCategoryService.selGoodsCategoryJson();
        System.out.println("tree"+json);
        return json;
    }

    @PostMapping("/treeGoodsCategoryChange")
    public String treeGoodsCategoryChange(){
        String json = goodsCategoryService.selGoodsCategoryJsonChange();
        System.out.println("tree"+json);
        return json;
    }

    @PostMapping("/addGoodsCategory")
    public String addGoodsCategory(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        goodsCategory.setCategoryName(jsonObject.getString("categoryName"));
        goodsCategory.setLevel(jsonObject.getInteger("level"));
        goodsCategory.setPid(jsonObject.getInteger("pid"));
        map = new HashMap<>();
        flag = goodsCategoryService.addGoodsCategory(goodsCategory);
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

        @PostMapping("/updateGoodsCategory")
    public String updateGoodsCategory(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        goodsCategory.setId(jsonObject.getInteger("id"));
        goodsCategory.setCategoryName(jsonObject.getString("categoryName"));
        goodsCategory.setLevel(jsonObject.getInteger("level"));
        goodsCategory.setPid(jsonObject.getInteger("pid"));
        map = new HashMap<>();
        flag = goodsCategoryService.updateGoodsCategory(goodsCategory);
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeGoodsCategory/{id}")
    public String removeUser(@PathVariable Integer id) {
        flag = goodsCategoryService.removeGoodsCategory(id);
        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }


}
