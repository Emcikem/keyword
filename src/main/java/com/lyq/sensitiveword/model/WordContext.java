package com.lyq.sensitiveword.model;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 应用上下文
 */
public class WordContext {

    /**
     * 忽略大小写
     */
    private boolean ignoreCase;

    /**
     * 忽略半角全角
     */
    private boolean ignoreWidth;

    /**
     * 是否忽略数字格式
     */
    private boolean ignoreNumStyle;

    /**
     * 是否进行敏感数字检测
     */
    private boolean sensitiveCheckNum;

    /**
     * 是否忽略中文繁简体
     */
    private boolean ignoreChineseStyle;

    /**
     * 是否忽略英文的写法
     */
    private boolean ignoreEnglishStyle;

    /**
     * 忽略重复词
     */
    private boolean ignoreRepeat;

    /**
     * 是否进行邮箱测试
     */
    private boolean sensitiveCheckEmail;

    /**
     * 是否进行 url 测试
     */
    private boolean sensitiveCheckUrl;

    public boolean ignoreCase() {
        return ignoreCase;
    }

    public WordContext ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    public boolean ignoreWidth() {
        return ignoreWidth;
    }

    public WordContext ignoreWidth(boolean ignoreWidth) {
        this.ignoreWidth = ignoreWidth;
        return this;
    }

    public boolean ignoreNumStyle() {
        return ignoreNumStyle;
    }

    public WordContext ignoreNumStyle(boolean ignoreNumStyle) {
        this.ignoreNumStyle = ignoreNumStyle;
        return this;
    }

    public boolean sensitiveCheckNum() {
        return sensitiveCheckNum;
    }

    public WordContext sensitiveCheckNum(boolean sensitiveCheckNum) {
        this.sensitiveCheckNum = sensitiveCheckNum;
        return this;
    }

    public boolean ignoreChineseStyle() {
        return ignoreChineseStyle;
    }

    public WordContext ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.ignoreChineseStyle = ignoreChineseStyle;
        return this;
    }

    public boolean ignoreEnglishStyle() {
        return ignoreEnglishStyle;
    }

    public WordContext ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.ignoreEnglishStyle = ignoreEnglishStyle;
        return this;
    }

    public boolean ignoreRepeat() {
        return ignoreRepeat;
    }

    public WordContext ignoreRepeat(boolean ignoreRepeat) {
        this.ignoreRepeat = ignoreRepeat;
        return this;
    }

    public boolean sensitiveCheckEmail() {
        return sensitiveCheckEmail;
    }

    public WordContext sensitiveCheckEmail(boolean sensitiveCheckEmail) {
        this.sensitiveCheckEmail = sensitiveCheckEmail;
        return this;
    }

    public boolean sensitiveCheckUrl() {
        return sensitiveCheckUrl;
    }

    public WordContext sensitiveCheckUrl(boolean sensitiveCheckUrl) {
        this.sensitiveCheckUrl = sensitiveCheckUrl;
        return this;
    }
}
