package com.xinyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.GoodsCategory;
import com.xinyu.mapper.goods.GoodsCategoryMapper;
import com.xinyu.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    private GoodsCategory goodsCategory;

    @Override
    public List<GoodsCategory> selGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.selGoodsCategory(goodsCategory);
    }

    @Override
    public String selGoodsCategoryJson(){
        goodsCategory = new GoodsCategory();
        goodsCategory.setLevel(1);
        List<GoodsCategory> goodsCategories1 = goodsCategoryMapper.selGoodsCategory(goodsCategory);
        goodsCategory.setLevel(null);
        Map<String,Object> map1;
        List<Object> list1 = new ArrayList<>();
        for (GoodsCategory category1 : goodsCategories1) {
            map1 = new HashMap<>();
            map1.put("id",category1.getId());
            map1.put("categoryName",category1.getCategoryName());
            map1.put("level",category1.getLevel());
            map1.put("effective",category1.getEffective());
            map1.put("icon",category1.getIcon());
            goodsCategory.setPid(category1.getId());
            List<GoodsCategory> goodsCategories2 = goodsCategoryMapper.selGoodsCategory(goodsCategory);
            Map<String,Object> map2;
            List<Object> list2 = new ArrayList<>();
            for (GoodsCategory category2 : goodsCategories2) {
                map2 = new HashMap<>();
                map2.put("id",category2.getId());
                map2.put("categoryName",category2.getCategoryName());
                map2.put("level",category2.getLevel());
                map2.put("effective",category2.getEffective());
                goodsCategory.setPid(category2.getId());
                List<GoodsCategory> goodsCategories3 = goodsCategoryMapper.selGoodsCategory(goodsCategory);
                Map<String,Object> map3;
                List<Object> list3 = new ArrayList<>();
                for (GoodsCategory category3 : goodsCategories3) {
                    map3 = new HashMap<>();
                    map3.put("id",category3.getId());
                    map3.put("categoryName",category3.getCategoryName());
                    map3.put("level",category3.getLevel());
                    map3.put("effective",category3.getEffective());
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

    @Override
    public String selGoodsCategoryJsonChange(){
        goodsCategory = new GoodsCategory();
        goodsCategory.setLevel(1);
        List<GoodsCategory> goodsCategories1 = goodsCategoryMapper.selGoodsCategory(goodsCategory);
        goodsCategory.setLevel(null);
        Map<String,Object> map1;
        List<Object> list1 = new ArrayList<>();
        for (GoodsCategory category1 : goodsCategories1) {
            map1 = new HashMap<>();
            map1.put("id",category1.getId());
            map1.put("categoryName",category1.getCategoryName());
            map1.put("level",category1.getLevel());
            map1.put("effective",category1.getEffective());
            goodsCategory.setPid(category1.getId());
            List<GoodsCategory> goodsCategories2 = goodsCategoryMapper.selGoodsCategory(goodsCategory);
            Map<String,Object> map2;
            List<Object> list2 = new ArrayList<>();
            for (GoodsCategory category2 : goodsCategories2) {
                map2 = new HashMap<>();
                map2.put("id",category2.getId());
                map2.put("categoryName",category2.getCategoryName());
                map2.put("level",category2.getLevel());
                map2.put("effective",category2.getEffective());
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
    public boolean addGoodsCategory(GoodsCategory goodsCategory){
        return goodsCategoryMapper.addGoodsCategory(goodsCategory)>0;
    }

    @Override
    public boolean removeGoodsCategory(Integer id){
        return goodsCategoryMapper.removeGoodsCategory(id)>0;
    }

    @Override
    public boolean updateGoodsCategory(GoodsCategory goodsCategory){
        return goodsCategoryMapper.updateGoodsCategory(goodsCategory)>0;
    }
}
