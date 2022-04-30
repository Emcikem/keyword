package com.lyq.sensitive.word.service;

import com.lyq.sensitive.word.WordContext;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 敏感词 map
 */
public interface IWordMap {

    /**
     * 是否包含敏感词
     */
    boolean contains(String target, WordContext context);
}
