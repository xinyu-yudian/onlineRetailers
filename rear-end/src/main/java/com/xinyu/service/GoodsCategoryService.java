package com.xinyu.service;

import com.xinyu.entity.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {
    List<GoodsCategory> selGoodsCategory(GoodsCategory goodsCategory);

    String selGoodsCategoryJson();

    String selGoodsCategoryJsonChange();

    boolean addGoodsCategory(GoodsCategory goodsCategory);

    boolean removeGoodsCategory(Integer id);

    boolean updateGoodsCategory(GoodsCategory goodsCategory);
}
