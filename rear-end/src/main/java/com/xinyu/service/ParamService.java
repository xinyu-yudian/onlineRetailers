package com.xinyu.service;

import com.xinyu.entity.param.Param;
import com.xinyu.entity.param.ParamChild;

import java.util.List;

public interface ParamService {

    List<ParamChild> selParamChildList(ParamChild child);

    List<Param> selParamList(Param param);
    String paramJson(Integer cid);

    boolean addParam(Param param);

    boolean updateParam(Param param);

    boolean removeParam(Integer id);

    boolean addParamChild(ParamChild paramChild);

    boolean updateParamChild(ParamChild paramChild);

    boolean removeParamChild(Integer id);
}
