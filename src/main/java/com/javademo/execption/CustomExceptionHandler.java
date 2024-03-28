package com.javademo.execption;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-28 10:51
 **/

import com.javademo.util.JsonData;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MyBatisSystemException.class)
    public JsonData handleMyBatisSystemException(HttpServletRequest request, MyBatisSystemException e) {
        logger.error(">>> Handle MyBatisSystemException, url is {}", request.getRequestURI(), e);
        return JsonData.buildError(1007, "数据库访问异常");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonData handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        logger.error(">>> Handle MissingServletRequestParameterException, url is {}", request.getRequestURI(), e);
        return JsonData.buildError(2002, "缺少必填参数");
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public JsonData handleSQLException(HttpServletRequest request, SQLException e) {
        logger.error(">>> Handle SQLException, url is {}", request.getRequestURI(), e);
        return JsonData.buildError(1008, "数据库语句异常");
    }

    @ExceptionHandler(value = JavaDemoException.class)
    @ResponseBody
    public JsonData handle(HttpServletRequest request, JavaDemoException e){

        String message = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        message = message.replaceAll("Response Error,status:\"(\\S+)\",msg：\"(\\S+)\",error:(\\S+)", "$2");

        logger.error(">>> Handle JavaDemoException, url is {}, msg is {}", request.getRequestURI(), message);

        return JsonData.buildError(e.getCode(), message);

    }

}
