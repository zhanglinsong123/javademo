package com.javademo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-28 16:06
 **/
@Repository
public interface TestMapper {

    List<Map<String, Object>> queryAll();
}
