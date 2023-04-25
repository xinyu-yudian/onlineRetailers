package com.xinyu.mapper.goods.attr;

import com.xinyu.entity.attr.Attr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttrMapper {
    List<Attr> selAttrList(Attr attr);

    int addAttr(Attr attr);

    int updateAttr(Attr attr);

    int removeAttr(Integer id);
}
