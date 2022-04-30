package com.lyq.sensitive.word.service.format;

import com.lyq.sensitive.word.constant.emnu.FormatEnum;
import com.lyq.sensitive.word.model.IWordContext;
import com.lyq.sensitive.word.service.ICharFormatService;
import org.springframework.stereotype.Service;


/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public abstract class IgnoreWidthCharFormat implements ICharFormatService {


    @Override
    public char format(char original, IWordContext context) {
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
