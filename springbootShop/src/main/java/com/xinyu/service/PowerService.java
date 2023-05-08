package com.xinyu.service;

import com.xinyu.entity.Power;

import java.util.List;

public interface PowerService {
    List<Power> selPowers(Power power);
    String treePowers();
}
