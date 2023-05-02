package com.xinyu.service.impl;

import com.xinyu.entity.Goods;
import com.xinyu.mapper.goods.GoodsMapper;
import com.xinyu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> selGoodsList(Goods goods) {
        return goodsMapper.selGoodsList(goods);
    }

    @Override
    public boolean addGoods(Goods goods) {
        return goodsMapper.addGoods(goods)>0;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods)>0;
    }

    @Override
    public boolean removeGoods(Integer id) {
        return goodsMapper.removeGoods(id)>0;
    }

    @Override
    public boolean updateNumber(Goods goods){
        return goodsMapper.updateNumber(goods)>0;
    }
}
