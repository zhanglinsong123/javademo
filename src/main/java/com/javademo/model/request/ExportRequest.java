package com.javademo.model.request;

import lombok.Data;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-29 09:40
 **/
@Data
public class ExportRequest {
    private String exportType;
    private String exportId;
}
