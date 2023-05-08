package com.xinyu.service;

import com.xinyu.entity.Report;

import java.util.List;

public interface ReportService {
    List<String> selArea();

    List<String> selDate();

    List<Integer> selCount(Report report);

}
