package com.xinyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.Power;
import com.xinyu.mapper.power.PowerMapper;
import com.xinyu.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("powerService")
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private Power power;

    @Override
    public List<Power> selPowers(Power power) {
        return powerMapper.selPowers(power);
    }

    @Override
    public String treePowers(){
        power = new Power();
        power.setLevel(1);
        List<Power> powerList1 = powerMapper.selPowers(power);
        List<Object> list1 = new ArrayList<>();
        Map<String,Object> map1;
        for(Power power1 : powerList1){
            map1 = new HashMap<>();
            map1.put("id", power1.getId());
            map1.put("powerName", power1.getPowerName());
            power.setLevel(null);
            power.setPid(power1.getId());
            List<Power> powerList2 = powerMapper.selPowers(power);
            List<Object> list2 = new ArrayList<>();
            Map<String,Object> map2;
            for (Power power2 : powerList2) {
                map2 = new HashMap<>();
                map2.put("id", power2.getId());
                map2.put("powerName", power2.getPowerName());
                power.setPid(power2.getId());
                List<Power> powerList3 = powerMapper.selPowers(power);
                List<Object> list3 = new ArrayList<>();
                Map<String,Object> map3;
                for (Power power3 : powerList3) {
                    map3 = new HashMap<>();
                    map3.put("id", power3.getId());
                    map3.put("powerName", power3.getPowerName());
                    list3.add(map3);
                    map3 = null;
                }
                map2.put("children",list3);
                list2.add(map2);
                map2 = null;
            }
            map1.put("children",list2);
            list1.add(map1);
            map1 = null;
        }
        return JSON.toJSONString(list1);
    }
}
