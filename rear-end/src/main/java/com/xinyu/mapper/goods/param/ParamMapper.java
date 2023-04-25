package com.xinyu.mapper.goods.param;

import com.xinyu.entity.attr.Attr;
import com.xinyu.entity.param.Param;

import java.util.List;

public interface ParamMapper {
    List<Param> selParamList(Param param);

    int addParam(Param param);

    int updateParam(Param param);

    int removeParam(Integer id);
}
