package com.lyq.sensitive.word.service;

import com.lyq.sensitive.word.model.WordResult;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 敏感词的结果处理
 */
public interface IWordResultHandler<R> {

    R handle(final WordResult wordResult);
}
