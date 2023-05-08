package com.xinyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.param.Param;
import com.xinyu.entity.param.ParamChild;
import com.xinyu.mapper.goods.param.ParamChildMapper;
import com.xinyu.mapper.goods.param.ParamMapper;
import com.xinyu.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("paramService")
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private ParamChildMapper paramChildMapper;

    private Param param;

    private ParamChild paramChild;


    @Override
    public List<ParamChild> selParamChildList(ParamChild child) {
        return paramChildMapper.selParamChildList(child);
    }

    @Override
    public List<Param> selParamList(Param param) {
        return paramMapper.selParamList(param);
    }

    @Override
    public String paramJson(Integer cid) {
        param = new Param();
        paramChild = new ParamChild();
        param.setCid(cid);
        List<Param> paramList1 = paramMapper.selParamList(param);
        param.setCid(null);
        Map<String,Object> map1;
        List<Object> list1 = new ArrayList<>();
        for (Param param1 : paramList1) {
            map1 = new HashMap<>();
            map1.put("id", param1.getId());
            map1.put("paramName", param1.getParamName());
            paramChild.setPid(param1.getId());
            List<ParamChild> childList1 =  paramChildMapper.selParamChildList(paramChild);
            Map<String,Object> map2;
            List<Object> list2 = new ArrayList<>();
            for (ParamChild child1: childList1) {
                map2 = new HashMap<>();
                map2.put("id",child1.getId());
                map2.put("childName",child1.getChildName());
                list2.add(map2);
                map2 = null;
            }
            map1.put("children",list2);
            list1.add(map1);
            map1 = null;
        }
        return JSON.toJSONString(list1);
    }

    @Override
    public boolean addParam(Param param) {
        return paramMapper.addParam(param)>0;
    }

    @Override
    public boolean updateParam(Param param) {
        return paramMapper.updateParam(param)>0;
    }

    @Override
    public boolean removeParam(Integer id) {
        return paramMapper.removeParam(id)>0;
    }

    @Override
    public boolean addParamChild(ParamChild paramChild) {
        return paramChildMapper.addParamChild(paramChild)>0;
    }

    @Override
    public boolean updateParamChild(ParamChild paramChild) {
        return paramChildMapper.updateParamChild(paramChild)>0;
    }

    @Override
    public boolean removeParamChild(Integer id) {
        return paramChildMapper.removeParamChild(id)>0;
    }
}
