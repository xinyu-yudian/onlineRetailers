package com.xinyu.mapper.goods.param;

import com.xinyu.entity.param.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParamMapper {
    List<Param> selParamList(Param param);

    int addParam(Param param);

    int updateParam(Param param);

    int removeParam(Integer id);
}
