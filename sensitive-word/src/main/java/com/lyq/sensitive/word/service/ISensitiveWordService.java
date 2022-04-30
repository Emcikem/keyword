package com.lyq.sensitive.word.service;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/29
 * @desc 敏感词工具类
 */
public interface ISensitiveWordService {

    /**
     * 是否包含敏感词
     */
    boolean contains(final String target);

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的
     * 2. 如果不存在，返回空列表
     */
    List<String> findAll(final String target);

    /**
     * 返回第一个敏感词
     */
    String findFirst(final String target);


    /**
     * 替换所有内容，自定义替换策略
     */
    String replace(final String target, final ISensitiveWordReplace replace);

    /**
     * 替换所有内容
     */
    String replace(final String target, final char replaceChar);

    /**
     * 默认用 * 替换
     */
    String replace(final String target);

    /**
     * 返回第一个敏感词
     */
    <R> List<R> findAll(final String target, final IWordResultHandler<R> handler);


    /**
     * 返回第一个敏感词
     */
    <R> R findFirst(final String target, final IWordResultHandler<R> handler);

}
