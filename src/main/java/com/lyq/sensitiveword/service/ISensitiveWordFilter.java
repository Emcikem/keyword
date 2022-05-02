package com.lyq.sensitiveword.service;

import com.lyq.sensitiveword.model.SensitiveWordContext;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc
 */
public interface ISensitiveWordFilter {

    /**
     * 初始化
     */
    void initSensitive();

    /**
     * 是否包含敏感词
     */
    boolean contains(String target);

    /**
     * 按照顺序返回所有的敏感词
     */
    List<String> findAll(String target);

    /**
     * 返回具体消息
     */
    List<SensitiveWordContext> findAllContext(String target);

    /**
     * 默认用*替换
     */
    String replace(String target);

    /**
     * 自定义替换词
     */
    String replace(String target, char ch);

    /**
     * 自定义替换策略
     */
    String replace(String target, ISensitiveWordReplace replace);
}
