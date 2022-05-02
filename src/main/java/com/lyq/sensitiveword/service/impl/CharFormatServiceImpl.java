package com.lyq.sensitiveword.service.impl;

import com.lyq.sensitiveword.constant.emnu.FormatEnum;
import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ICharFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public abstract class CharFormatServiceImpl implements ICharFormatService {

    private final Map<String, ICharFormatService> formatMap;

    @Autowired
    public CharFormatServiceImpl(List<ICharFormatService> charFormatServices) {
        formatMap = charFormatServices.stream().collect(Collectors.toMap(ICharFormatService::getType, Function.identity()));
    }

    @Override
    public char format(char original, WordContext context) {
        char result = original;

        List<ICharFormatService> charFormats = new ArrayList<>();
        if (context.ignoreCase()) {
            charFormats.add(formatMap.get(FormatEnum.CASECHAR.getType()));
        }


        for (ICharFormatService charFormat : charFormats) {
            result = charFormat.format(result, context);
        }
        return result;
    }
}
