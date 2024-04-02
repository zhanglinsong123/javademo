package com.javademo.util;

import com.alibaba.fastjson.JSONObject;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import java.util.Locale;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-02 09:27
 **/
public class PhoneToRegionUtil {

    /**
     * 手机号基本工具类
     */
    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    /**
     * 手机号运营商映射类
     */
    private static final PhoneNumberToCarrierMapper CARRIER_MAPPER = PhoneNumberToCarrierMapper.getInstance();

    /**
     * 手机号归属地映射类
     */
    private static final PhoneNumberOfflineGeocoder GEOCODER = PhoneNumberOfflineGeocoder.getInstance();


    private static final String INVALID_NUMBER = "无效号码";

    private static final Integer COUNTRY_CODE = 86;

    /**
     * 获取手机号码信息
     * @param phone
     * @return
     */
    public static JSONObject getPhoneInfo(String phone) {
        JSONObject affiliation = new JSONObject();
        affiliation.put("phone", phone);
        affiliation.put("carrier", getPhoneCarrier(phone));
        affiliation.put("region", getPhoneRegionInfo(phone));
        return affiliation;
    }

    /**
     * 获取手机号运营商
     * @param phone
     * @return
     */
    private static String getPhoneCarrier(String phone) {
        return isValidNumber(phone) ? CARRIER_MAPPER.getNameForNumber(getPhoneNumber(phone), Locale.CHINA) : INVALID_NUMBER;
    }

    /**
     * 获取手机号归属地
     * @param phone
     * @return
     */
    private static String getPhoneRegionInfo(String phone) {
        return isValidNumber(phone) ? GEOCODER.getDescriptionForNumber(getPhoneNumber(phone), Locale.CHINESE) : INVALID_NUMBER;
    }

    /**
     * 验证当前手机号是否有效
     * @param phone
     * @return
     */
    private static boolean isValidNumber(String phone) {
        return PHONE_NUMBER_UTIL.isValidNumber(getPhoneNumber(phone));
    }

    /**
     * 生成Phonenumber.PhoneNumber对象
     * @param phone
     * @return
     */
    private static Phonenumber.PhoneNumber getPhoneNumber(String phone) {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        phoneNumber.setCountryCode(COUNTRY_CODE);
        phoneNumber.setNationalNumber(Long.parseLong(phone));
        return phoneNumber;
    }
}
