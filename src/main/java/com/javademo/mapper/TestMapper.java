package com.javademo.mapper;

import com.javademo.model.entity.EntAppManageEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from `ent_apiceshiqiye_app_manage`;")
    List<EntAppManageEntity> queryAllEntAppManage();
}
