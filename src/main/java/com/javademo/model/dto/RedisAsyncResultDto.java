package com.javademo.model.dto;

import lombok.Data;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-29 09:55
 **/
@Data
public class RedisAsyncResultDto {
    private Integer total;
    private Integer done;
    private String key;
    private Boolean success;
    private String url = "";
    private Boolean flag = false;
    private String msg = "";
}
