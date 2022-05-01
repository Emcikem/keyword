package com.lyq.sensitive.word.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Emcikem
 * @create 2022/5/1
 * @desc
 */
public class SensitiveWordFilter {

    /**
     * 根节点
     */
    public TrieNode root = new TrieNode();


    /**
     * 添加
     */
    public void insert(String sensitiveWord) {
        TrieNode p = root;
        for (int i = 0; i < sensitiveWord.length(); i++) {
            char word = sensitiveWord.charAt(i);
            TrieNode child = p.getChildNode(word);
            if (child == null) {
                p.setChildNode(word);
            }
            p = p.getChildNode(word);
        }
        p.setLength(sensitiveWord.length());
        p.setEndChar(true);
    }

    public void getFail() {
        Queue<TrieNode> nodes = new LinkedList<>();
        root.setFailNode(null);
        for (TrieNode node : root.getChildren().values()) {
            node.setFailNode(root);
            nodes.add(node);
        }
        while (!nodes.isEmpty()) {
            TrieNode currentNode = nodes.remove();
            for (Character ch : currentNode.getChildrenDataList()) {
                TrieNode child = currentNode.getChildNode(ch);
                nodes.add(child);

                // 我fail指针是我父亲的fail指针的节点作为根时，其孩子节点下和我值相同的那个节点，默认指向根节点
                TrieNode childNode = currentNode.getFailNode().getChildNode(ch);
                if (childNode == null) {
                    child.setFailNode(root);
                } else {
                    child.setFailNode(childNode);
                }
            }
        }
    }

    public void query(String str) {
        TrieNode currentNode = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (currentNode == null) {
                currentNode = root;
            }
            currentNode = currentNode.getChildNode(ch);
            for (TrieNode node = currentNode; node != root && node != null; node = node.getFailNode()) {
                if (node.isEndChar()) {
                    System.out.printf("敏感词：[%d, %d], %s\n", i - node.getLength() + 1, i, str.substring(i - node.getLength() + 1, i + 1));
                }
            }
            if (currentNode != null && currentNode.isEndChar()) {
                currentNode = currentNode.getFailNode();
            }
        }
    }

    public static void main(String[] args) {
        SensitiveWordFilter filter = new SensitiveWordFilter();
        readText("/Users/emcikem/IdeaProjects/keyword/sensitive-word/src/main/java/com/lyq/sensitive/word/test/sensitive.txt", filter);
        filter.getFail();
        String str = readText("/Users/emcikem/IdeaProjects/keyword/sensitive-word/src/main/java/com/lyq/sensitive/word/test/passage.txt");
        long l = System.currentTimeMillis();
        assert str != null;
        filter.query(str);
        long e = System.currentTimeMillis();
        System.out.println(e - l);
    }


    public static void readText(String path, SensitiveWordFilter filter) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String lineTxt;
            // 逐行读取
            while ((lineTxt = br.readLine()) != null) {
                // 输出内容到控制台
                filter.insert(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readText(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String lineTxt;
            StringBuilder str = new StringBuilder();
            // 逐行读取
            while ((lineTxt = br.readLine()) != null) {
                // 输出内容到控制台
                str.append(lineTxt).append("\n");
            }
            br.close();
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
