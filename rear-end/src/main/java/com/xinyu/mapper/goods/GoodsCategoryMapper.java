package com.xinyu.mapper.goods;

import com.xinyu.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper {
    List<GoodsCategory> selGoodsCategory(GoodsCategory goodsCategory);

    int addGoodsCategory(GoodsCategory goodsCategory);

    int removeGoodsCategory(Integer id);

    int updateGoodsCategory(GoodsCategory goodsCategory);
}
