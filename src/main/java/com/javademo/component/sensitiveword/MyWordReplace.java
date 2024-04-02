package com.javademo.component.sensitiveword;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordReplace;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-02 15:22
 **/
public class MyWordReplace implements IWordReplace {
    @Override
    public void replace(StringBuilder stringBuilder, char[] chars, IWordResult iWordResult, IWordContext iWordContext) {
        String sensitiveWord = InnerWordCharUtils.getString(chars, iWordResult);
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if("五星红旗".equals(sensitiveWord)) {
            stringBuilder.append("国家旗帜");
        } else if("毛主席".equals(sensitiveWord)) {
            stringBuilder.append("教员");
        } else {
            // 其他默认使用 * 代替
            int wordLength = iWordResult.endIndex() - iWordResult.startIndex();
            for(int i = 0; i < wordLength; i++) {
                stringBuilder.append('*');
            }
        }
    }
}
