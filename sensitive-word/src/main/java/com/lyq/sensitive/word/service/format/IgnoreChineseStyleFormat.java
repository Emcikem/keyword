package com.lyq.sensitive.word.service.format;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
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
public class IgnoreChineseStyleFormat implements ICharFormatService {

    @Override
    public char format(char original, IWordContext context) {
        return ZhConverterUtil.toSimple(String.valueOf(original)).charAt(0);
    }

    @Override
    public String getType() {
        return FormatEnum.CHINESESTYLE.getType();
    }
}
