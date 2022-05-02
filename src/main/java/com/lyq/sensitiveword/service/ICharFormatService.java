package com.lyq.sensitiveword.service;

import com.lyq.sensitiveword.model.WordContext;

/**
 * 1. 英文字母替换
 * 2. 数字替换
 * 3. 大小写转换
 * 4. 繁体简体替换
 * 5. 拆字
 * 6. 半角全角符号
 * 7. 形近字不同编码替换
 * @author Emcikem
 * @create 2022/4/30
 */
public interface ICharFormatService {

    char format(char original, WordContext context);

    String getType();
}
