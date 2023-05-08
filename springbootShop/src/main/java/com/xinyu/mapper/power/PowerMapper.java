package com.xinyu.mapper.power;

import com.xinyu.entity.Power;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PowerMapper {
    List<Power> selPowers(Power power);
}
