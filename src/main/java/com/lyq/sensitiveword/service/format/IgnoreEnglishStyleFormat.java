package com.lyq.sensitiveword.service.format;

import com.lyq.sensitiveword.constant.emnu.FormatEnum;
import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ICharFormatService;
import com.lyq.sensitiveword.utils.CharUtils;
import org.springframework.stereotype.Service;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public class IgnoreEnglishStyleFormat implements ICharFormatService {

    @Override
    public char format(char original, WordContext context) {
        return CharUtils.getMappingChar(original);
    }

    @Override
    public String getType() {
        return FormatEnum.ENGLISHSTYLE.getType();
    }
}
