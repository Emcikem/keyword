package com.lyq.sensitive.word.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emcikem
 * @create 2022/5/1
 * @desc
 */
public class TrieNode {

    /**
     * 节点值
     */
    private char data;

    /**
     * 是否是模式串的结束节点
     */
    private boolean isEndChar = false;

    /**
     * 失败节点
     */
    private TrieNode failNode;

    /**
     * 模式串长度
     */
    private int length = 0; // 模式串长度

    /**
     * 子节点
     */
    private Map<Character, TrieNode> children;

    private TrieNode nex;

    public TrieNode() {}

    public TrieNode(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public boolean isEndChar() {
        return isEndChar;
    }

    public void setEndChar(boolean endChar) {
        isEndChar = endChar;
    }

    public TrieNode getFailNode() {
        return failNode;
    }

    public void setFailNode(TrieNode failNode) {
        this.failNode = failNode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildNode(Character data) {
        TrieNode node = new TrieNode(data);
        if (children == null) {
            children = new HashMap<>();
        }
        children.put(data, node);
    }

    public TrieNode getChildNode(Character data) {
        if (children == null) {
            return null;
        }
        return children.get(data);
    }

    public Collection<Character> getChildrenDataList() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children.keySet();
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "data=" + data +
                ", isEndChar=" + isEndChar +
                ", length=" + length +
                ", children=" + children +
                '}';
    }
}
