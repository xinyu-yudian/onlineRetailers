package com.xinyu.service;

import com.xinyu.entity.attr.Attr;
import com.xinyu.entity.attr.AttrChild;

import java.util.List;

public interface AttrService {
    List<Attr> selAttrList(Attr attr);

    List<AttrChild> selAttrChildList(AttrChild attrChild);

    String attrJson(Integer cid);

    boolean addAttr(Attr attr);

    boolean updateAttr(Attr attr);

    boolean removeAttr(Integer id);

    boolean addAttrChild(AttrChild attrChild);

    boolean updateAttrChild(AttrChild attrChild);

    boolean removeAttrChild(Integer id);

}
