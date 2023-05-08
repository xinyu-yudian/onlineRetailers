package com.xinyu.mapper.report;

import com.xinyu.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
   List<String> selArea();

   List<String> selDate();

   List<Integer> selCount(Report report);
}
