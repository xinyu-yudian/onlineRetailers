package com.xinyu.service.impl;

import com.xinyu.entity.Report;
import com.xinyu.mapper.report.ReportMapper;
import com.xinyu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private Report report;

    @Override
    public List<String> selArea() {
        return reportMapper.selArea();
    }

    @Override
    public List<String> selDate() {
        return reportMapper.selDate();
    }

    @Override
    public List<Integer> selCount(Report report) {
        return reportMapper.selCount(report);
    }



}
