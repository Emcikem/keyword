package com.lyq.sensitive.word.service.format;

import com.lyq.sensitive.word.constant.emnu.FormatEnum;
import com.lyq.sensitive.word.model.IWordContext;
import com.lyq.sensitive.word.service.ICharFormatService;
import com.lyq.sensitive.word.utils.NumUtils;
import org.springframework.stereotype.Service;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public class IgnoreNumberStyleCharFormat implements ICharFormatService {

    @Override
    public char format(char original, IWordContext context) {
        return NumUtils.getMappingChar(original);
    }

    @Override
    public String getType() {
        return FormatEnum.NUMBERSTYLE.getType();
    }
}
