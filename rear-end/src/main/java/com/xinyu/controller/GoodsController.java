package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyu.entity.Goods;
import com.xinyu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Goods goods;
    private Map<String, Object> map;
    private static final Integer pageSize = 5;
    private Integer start = 0;
    private Integer pageNow = 1;

    private Map<String, Object> fileMap = new HashMap<>();

    private Boolean flag = false;

    @PostMapping("/getGoodsList")
    public String getGoodsList(@RequestBody String params) {
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        goods.setId(jsonObject.getInteger("id"));
        goods.setGoodsName(jsonObject.getString("goodsName"));
        pageNow = jsonObject.getInteger("pageNow");

        start = (pageNow-1)*pageSize;
        PageHelper.offsetPage(start, pageSize);
        List<Goods> goodsList = goodsService.selGoodsList(goods);
        PageInfo pageInfo = new PageInfo<>(goodsList);
        System.out.println(pageInfo);

        long total = pageInfo.getTotal();
        long pageTotal = total%pageSize==0 ? total/pageSize : total/pageSize+1;

        map = new HashMap<String, Object>();
        if(goodsList != null){
            map.put("status",200);
            map.put("data", JSON.toJSON(goodsList));
            map.put("pageNow",pageNow);
            map.put("pageTotal",pageTotal);
            map.put("total",total);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/addGoods")
    public String addGoods(@RequestBody String param,HttpServletRequest request) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);

        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        goods.setGoodsName(jsonObject.getString("goodsName"));
        goods.setPrice(jsonObject.getDouble("price"));
        goods.setNumber(jsonObject.getInteger("number"));
        goods.setWeight(jsonObject.getDouble("weight"));
        goods.setAddTime(nowTime);
        goods.setIcon(setIcon(request));
        goods.setCatId(jsonObject.getInteger("catId"));
        goods.setAttrs(jsonObject.getString("attrs"));
        goods.setParams(jsonObject.getString("params"));
        goods.setIntroduce(jsonObject.getString("introduce"));
        flag = goodsService.addGoods(goods);

        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateGoods")
    public String updateGoods(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        goods.setId(jsonObject.getInteger("id"));
        goods.setGoodsName(jsonObject.getString("goodsName"));
        goods.setPrice(jsonObject.getDouble("price"));
        goods.setNumber(jsonObject.getInteger("number"));
        goods.setWeight(jsonObject.getDouble("weight"));
        goods.setAddTime(jsonObject.getString("addTime"));
        goods.setIcon(jsonObject.getString("icon"));
        goods.setCatId(jsonObject.getInteger("catId"));
        flag = goodsService.updateGoods(goods);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeGoods/{id}")
    public String removeUser(@PathVariable Integer id) {
        flag = goodsService.removeGoods(id);

        map = new HashMap<String, Object>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/upload")
    public String updateAttrChild(MultipartFile file){
        System.out.println(file);
        fileMap.put(file.getName(),file);
        map = new HashMap<>();
        if (flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @DeleteMapping("/removeIcon/{iconName}")
    public String removeUser(@PathVariable String iconName) {
        System.out.println(iconName);
        fileMap.remove(iconName);
        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    public String setIcon( HttpServletRequest request){
        List<String> iconArr = new ArrayList<>();
        System.out.println(fileMap);
        MultipartFile file;
        for (String fileName : fileMap.keySet()) {
            file = (MultipartFile)fileMap.get(fileName);
            String srcPath = file.getOriginalFilename();
            if (!"".equals(srcPath)) {
                //HttpServletRequest为了获取后台猫的绝对路径
                String tomcatPath = request.getServletContext().getRealPath("/images");
                //实现文件上传
                //获取上传文件的后缀
                String suffix = srcPath.substring(srcPath.lastIndexOf("."));
                //获取UUID
                UUID uuid = UUID.randomUUID();
                String prefix = uuid.toString().replace("-", "").toUpperCase();
                //完整文件名
                String newFileName = prefix + suffix;
                iconArr.add(newFileName);
                try {
                    file.transferTo(new File(tomcatPath, fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        fileMap = new HashMap<>();
        return iconArr.toString();
    }
}
