package com.javademo.execption;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-28 10:51
 **/

import com.javademo.util.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){

        logger.error("[ 系统异常 ]{}",e.getMessage());

        if( e instanceof JavaDemoException ){

            JavaDemoException javaDemoException = (JavaDemoException) e;

            return JsonData.buildError(javaDemoException.getCode(), javaDemoException.getMsg());

        }else {

            return JsonData.buildError("全局异常，未知错误");

        }


    }

}
