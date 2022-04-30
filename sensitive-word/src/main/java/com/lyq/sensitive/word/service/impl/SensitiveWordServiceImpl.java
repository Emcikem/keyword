package com.lyq.sensitive.word.service.impl;

import com.lyq.sensitive.word.WordContext;
import com.lyq.sensitive.word.constant.CharConst;
import com.lyq.sensitive.word.service.ISensitiveWordService;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 敏感词引导类
 */
public class SensitiveWordServiceImpl implements ISensitiveWordService {

    private SensitiveWordServiceImpl() {}

    private final WordContext context = this.buildDefaultContext();


    public static SensitiveWordServiceImpl newInstance() {
        return new SensitiveWordServiceImpl();
    }

    /**
     * 初始化
     * 根据配置，初始化对应的map，比较消耗性能
     */
    public SensitiveWordServiceImpl init() {
        this.initWordMap();
        return this;
    }

    /**
     * 设置禁止的实现
     */
    public SensitiveWordServiceImpl wordDeny(IWordDeny wordDeny) {
        this.wordDeny = wordDeny;
        return this;
    }

    /**
     * 设置允许的实现
     */
    public SensitiveWordServiceImpl wordAllow(IWordAllow wordAllow) {
        this.wordAllow = wordAllow;
        return this;
    }

    /**
     * 设置是否启动数字检测
     */
    public SensitiveWordServiceImpl enableNumCheck(boolean enableNumCheck) {
        this.context.sensitiveCheckNum(enableNumCheck);
        return this;
    }

    /**
     * 是否设置启动email检测
     */
    public SensitiveWordServiceImpl enableEmailCheck(boolean enableEmailCheck) {
        this.context.sensitiveCheckEmail(enableEmailCheck);
        return this;
    }

    /**
     * 设置是否启动Url检测
     */
    public SensitiveWordServiceImpl enableUrlCheck(boolean enableUrlCheck) {
        this.context.sensitiveCheckUrl(enableUrlCheck);
        return this;
    }

    /**
     * 是否忽略大小写
     */
    public SensitiveWordServiceImpl ignoreCase(boolean ignoreCase) {
        this.context.ignoreCase(ignoreCase);
        return this;
    }

    /**
     * 是否忽略半角全角
     */
    public SensitiveWordServiceImpl ignoreWidth(boolean ignoreWidth) {
        this.context.ignoreWidth(ignoreWidth);
        return this;
    }

    /**
     * 是否忽略数字格式
     */
    public SensitiveWordServiceImpl ignoreNumStyle(boolean ignoreNumStyle) {
        this.context.ignoreNumStyle(ignoreNumStyle);
        return this;
    }

    /**
     * 是否忽略中文样式
     */
    public SensitiveWordServiceImpl ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.context.ignoreChineseStyle(ignoreChineseStyle);
        return this;
    }

    /**
     * 是否忽略英文样式
     */
    public SensitiveWordServiceImpl ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.context.ignoreEnglishStyle(ignoreEnglishStyle);
        return this;
    }

    /**
     * 是否忽略重复
     */
    public SensitiveWordServiceImpl ignoreRepeat(boolean ignoreRepeat) {
        this.context.ignoreRepeat(ignoreRepeat);
        return this;
    }

    /**
     * 构建默认的上下文
     */
    public WordContext buildDefaultContext() {
        WordContext wordContext = WordContext.newInstance();

        wordContext.ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(true)
                .sensitiveCheckNum(true)
                .sensitiveCheckEmail(true)
                .sensitiveCheckUrl(true);

        return wordContext;
    }

    @Override
    public boolean contains(String target) {
        return false;
    }

    @Override
    public List<String> findAll(String target) {
        return null;
    }

    @Override
    public String findFirst(String target) {
        return null;
    }

    @Override
    public String replace(String target, ISensitiveWordReplace replace) {
        return null;
    }

    @Override
    public String replace(String target, char replaceChar) {
        return null;
    }

    @Override
    public String replace(String target) {
        return this.replace(target, CharConst.STAR);
    }

    @Override
    public <R> List<R> findAll(String target, IWordResultHandler<R> handler) {
        return null;
    }

    @Override
    public <R> R findFirst(String target, IWordResultHandler<R> handler) {
        return null;
    }
}
