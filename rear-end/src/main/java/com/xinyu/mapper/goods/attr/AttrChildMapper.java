package com.xinyu.mapper.goods.attr;

import com.xinyu.entity.attr.Attr;
import com.xinyu.entity.attr.AttrChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttrChildMapper {
    List<AttrChild> selAttrChildList(AttrChild attrChild);

    int addAttrChild(AttrChild attrChild);

    int updateAttrChild(AttrChild attrChild);

    int removeAttrChild(Integer id);
}
