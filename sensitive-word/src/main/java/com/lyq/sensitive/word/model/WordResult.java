package com.lyq.sensitive.word.model;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 敏感词
 */
public class WordResult {
    /**
     * 敏感词
     */
    private String word;

    /**
     * 开始下标
     */
    private int startIndex;

    /**
     * 结束下标
     */
    private int endIndex;

    public String word() {
        return word;
    }

    public WordResult word(String word) {
        this.word = word;
        return this;
    }

    public int startIndex() {
        return startIndex;
    }

    public WordResult startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public int endIndex() {
        return endIndex;
    }

    public WordResult endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @Override
    public String toString() {
        return "WordResult{" +
                "word='" + word + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}
