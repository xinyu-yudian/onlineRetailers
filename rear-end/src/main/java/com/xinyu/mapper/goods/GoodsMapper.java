package com.xinyu.mapper.goods;

import com.xinyu.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> selGoodsList(Goods goods);

    int addGoods(Goods goods);

    int updateGoods(Goods goods);

    int removeGoods(Integer id);

    int updateNumber(Goods goods);
}
