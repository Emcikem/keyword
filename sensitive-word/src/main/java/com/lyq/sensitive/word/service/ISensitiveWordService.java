package com.lyq.sensitive.word.service;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
public interface ISensitiveWordService {

    /**
     * 是否包含敏感词
     */
    boolean contains(final String target);


    /**
     * 返回所有敏感词
     */
    List<String> findAll(final String target);

    /**
     * 替换所有内容
     */
    String replace(final String target, final ISensitiveWordReplace replace);

    /**
     * 替换所有内容
     */
    String replace(final String target, final char replaceChar);

    /**
     * 默认用*替换所有内容
     */
    String replace(final String target);





}
