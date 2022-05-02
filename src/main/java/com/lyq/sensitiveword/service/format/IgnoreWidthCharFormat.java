package com.lyq.sensitiveword.service.format;

import com.lyq.sensitiveword.constant.emnu.FormatEnum;
import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ICharFormatService;
import org.springframework.stereotype.Service;


/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public abstract class IgnoreWidthCharFormat implements ICharFormatService {


    @Override
    public char format(char original, WordContext context) {
        return toHalfWidth(original);
    }

    @Override
    public String getType() {
        return FormatEnum.WIDTHCHAR.getType();
    }


    private char toHalfWidth(char c) {
        char resultChar = c;
        if (c == 12288) {
            resultChar = ' ';
        } else if (c > '\uff00' && c < '｟') {
            resultChar = (char)(c - 'ﻠ');
        }

        return resultChar;
    }

    private char toFullWidth(char c) {
        char resultChar = c;
        if (c == ' ') {
            resultChar = 12288;
        } else if (c >= '!' && c <= '~') {
            resultChar = (char)(c + 'ﻠ');
        }

        return resultChar;
    }
}
