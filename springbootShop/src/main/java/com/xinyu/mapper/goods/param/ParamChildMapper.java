package com.xinyu.mapper.goods.param;

import com.xinyu.entity.param.ParamChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParamChildMapper {
    List<ParamChild> selParamChildList(ParamChild child);

    int addParamChild(ParamChild paramChild);

    int updateParamChild(ParamChild paramChild);

    int removeParamChild(Integer id);
}
