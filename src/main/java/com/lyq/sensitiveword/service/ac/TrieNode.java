package com.lyq.sensitiveword.service.ac;

import java.util.*;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc
 */
public class TrieNode {
    private Map<Character, TrieNode> children;

    /**
     * fail指针
     */
    private TrieNode fail;

    /**
     * 是否是根节点
     */
    private Boolean isRoot = false;

    /**
     *
     */
    private boolean isEnd;

    private int length;

    public TrieNode() {
        children = new HashMap<>();
    }
    public TrieNode(Boolean isEnd) {
        this();
        this.isRoot = isEnd;
    }

    public TrieNode insert(Character ch) {
        TrieNode node = this.children.get(ch);
        if (node == null) {
            node = new TrieNode();
            children.put(ch, node);
        }
        return node;
    }

    public TrieNode find(Character ch) {
        return children.get(ch);
    }

    public TrieNode nextState(Character ch) {
        TrieNode state = this.find(ch);
        // 存在节点，直接走
        if (state != null) {
            return state;
        }
        // 根节点就跳回自己
        if (this.isRoot) {
            return this;
        }
        // 跳转到fail指针下和我同样字符的地方
        return this.fail.nextState(ch);
    }


    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public Collection<TrieNode> children() {
        return this.children.values();
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public TrieNode getFail() {
        return fail;
    }

    public void setFail(TrieNode fail) {
        this.fail = fail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
