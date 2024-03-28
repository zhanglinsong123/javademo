package com.javademo.execption;

import lombok.Getter;
import lombok.Setter;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-28 10:52
 **/
@Setter
@Getter
public class JavaDemoException extends RuntimeException {

    private Integer code;

    private String msg;

    public JavaDemoException(Integer code, String msg){
            this.code = code;
            this.msg = msg;
    }

}
