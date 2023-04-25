package com.xinyu.mapper.goods.param;

import com.xinyu.entity.param.Param;
import com.xinyu.entity.param.ParamChild;

import java.util.List;

public interface ParamChildMapper {
    List<ParamChild> selParamChildList(ParamChild child);

    int addParamChild(ParamChild paramChild);

    int updateParamChild(ParamChild paramChild);

    int removeParamChild(Integer id);
}
