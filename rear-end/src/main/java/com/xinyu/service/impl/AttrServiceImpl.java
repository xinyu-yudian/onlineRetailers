package com.xinyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.attr.Attr;
import com.xinyu.entity.attr.AttrChild;
import com.xinyu.mapper.goods.attr.AttrChildMapper;
import com.xinyu.mapper.goods.attr.AttrMapper;
import com.xinyu.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("attrService")
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrChildMapper attrChildMapper;

    @Autowired
    private Attr attr;

    @Autowired
    private AttrChild attrChild;

    @Override
    public List<Attr> selAttrList(Attr attr) {
        return attrMapper.selAttrList(attr);
    }

    @Override
    public List<AttrChild> selAttrChildList(AttrChild attrChild) {
        return attrChildMapper.selAttrChildList(attrChild);
    }

    @Override
    public String attrJson(Integer cid){
        attr.setCid(cid);
        List<Attr> attrList1 = attrMapper.selAttrList(attr);
        attr.setCid(null);
        Map<String,Object> map1;
        List<Object> list1 = new ArrayList<>();
        for (Attr attr1 : attrList1) {
            map1 = new HashMap<>();
            map1.put("id",attr1.getId());
            map1.put("attrName",attr1.getAttrName());
            attrChild.setAid(attr1.getId());
            List<AttrChild> childList1 = attrChildMapper.selAttrChildList(attrChild);
            Map<String,Object> map2;
            List<Object> list2 = new ArrayList<>();
            for (AttrChild child1 : childList1) {
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
    public boolean addAttr(Attr attr) {
        return attrMapper.addAttr(attr)>0;
    }

    @Override
    public boolean updateAttr(Attr attr) {
        return attrMapper.updateAttr(attr)>0;
    }

    @Override
    public boolean removeAttr(Integer id) {
        return attrMapper.removeAttr(id)>0;
    }

    @Override
    public boolean addAttrChild(AttrChild attrChild) {
        return attrChildMapper.addAttrChild(attrChild)>0;
    }

    @Override
    public boolean updateAttrChild(AttrChild attrChild) {
        return attrChildMapper.updateAttrChild(attrChild)>0;
    }

    @Override
    public boolean removeAttrChild(Integer id) {
        return attrChildMapper.removeAttrChild(id)>0;
    }


}
