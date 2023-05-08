package com.xinyu.service;

import com.xinyu.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> selGoodsList(Goods goods);

    boolean addGoods(Goods goods);

    boolean updateGoods(Goods goods);

    boolean removeGoods(Integer id);

    boolean updateNumber(Goods goods);
}
