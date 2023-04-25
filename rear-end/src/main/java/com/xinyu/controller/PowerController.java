package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyu.entity.Power;
import com.xinyu.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PowerController {

    @Autowired
    private PowerService powerService;

    @Autowired
    private Power power;

    private Map<String, Object> map;

    private static final Integer pageSize = 8;
    private Integer start = 0;
    private Integer pageNow = 1;

    @RequestMapping("/getPowers")
    public String getPowers(@RequestBody String param) {
        map = new HashMap<String, Object>();
        JSONObject jsonObject = JSON.parseObject(param);
        System.out.println(jsonObject);
        power.setId(jsonObject.getInteger("id"));
        power.setPid(jsonObject.getInteger("pid"));
        pageNow = jsonObject.getInteger("pageNow");

        start = (pageNow-1)*pageSize;
        PageHelper.offsetPage(start, pageSize);
        List<Power> powerList = powerService.selPowers(power);
        PageInfo pageInfo = new PageInfo<>(powerList);

        long total = pageInfo.getTotal();
        long pageTotal = total%pageSize==0 ? total/pageSize : total/pageSize+1;

        if (powerList != null){
            map.put("status",200);
            map.put("data",JSON.toJSON(powerList));
            map.put("pageNow",pageNow);
            map.put("pageTotal",pageTotal);
            map.put("total",total);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/treePowers")
    public String treePowers(){
        String json = powerService.treePowers();
        System.out.println("tree"+json);
        return json;
    }
}
