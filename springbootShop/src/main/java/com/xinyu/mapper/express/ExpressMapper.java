package com.xinyu.mapper.express;

import com.xinyu.entity.Express;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpressMapper {
    List<Express> selExpressions(String orderNumber);
}
