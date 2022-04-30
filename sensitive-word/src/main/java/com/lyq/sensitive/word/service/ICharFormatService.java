package com.lyq.sensitive.word.service;

import com.lyq.sensitive.word.model.IWordContext;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
public interface ICharFormatService {

    char format(char original, IWordContext context);

    String getType();
}
