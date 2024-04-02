package com.javademo.controller;

import com.javademo.util.JsonData;
import com.javademo.util.PhoneToRegionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: javademo
 * @Description: 获取手机号码归属地信息
 * @Author: zls
 * @Date: 2024-04-02 09:24
 **/
@RestController
public class PhoneToRegionController {

    @GetMapping("/getPhoneInfo/{phone}")
    public JsonData getPhoneInfo(@PathVariable("phone") String phone) {
        return JsonData.buildSuccess(PhoneToRegionUtil.getPhoneInfo(phone));
    }
}
