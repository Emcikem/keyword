package com.lyq.sensitiveword.model;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc
 */
public class SensitiveWordContext {

    /**
     * 敏感词
     */
    private String sensitiveWord;

    /**
     * 敏感词在target的起始位置
     */
    private int startIndex;

    /**
     * 敏感词在target的末位置
     */
    private int endIndex;

    public SensitiveWordContext(String sensitiveWord, int startIndex, int endIndex) {
        this.sensitiveWord = sensitiveWord;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
