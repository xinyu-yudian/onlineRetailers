package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.xinyu.entity.Report;
import com.xinyu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private Report report;

    private Map<String,Object> map;

    @GetMapping("/getReportData")
    public String getReportData(){
        map = new HashMap<>();
        List<String> areaList = reportService.selArea();
        List<String> dateList = reportService.selDate();

        map.put("areas", areaList);
        map.put("dates", dateList);

        List<Object> seriestList = new ArrayList<>();
        Map<String,Object> map2;
        List<Integer> countList;
        for (int i = 0; i < areaList.size(); i++) {
            map2 = new HashMap<>();
            report.setArea(areaList.get(i));
            countList = reportService.selCount(report);
            map2.put("name",areaList.get(i));
            map2.put("type","line");
            map2.put("stack","Total");
            map2.put("areaStyle","{}");
            map2.put("data",countList);
            seriestList.add(map2);
            map2 = null;
        }
        map.put("series",seriestList);
        return JSON.toJSONString(map);
    }
}
