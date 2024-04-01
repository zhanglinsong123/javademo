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

    private final Integer code;

    private final String message;

    public JavaDemoException(Integer code, String message){
            this.code = code;
            this.message = message;
    }

}
