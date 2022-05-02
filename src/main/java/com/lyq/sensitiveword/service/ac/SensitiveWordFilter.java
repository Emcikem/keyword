package com.lyq.sensitiveword.service.ac;

import com.lyq.sensitiveword.constant.CharConst;
import com.lyq.sensitiveword.model.SensitiveWordContext;
import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import com.lyq.sensitiveword.service.ISensitiveWordReplace;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Emcikem
 * @create 2022/5/1
 * @desc
 */
@Service
public class SensitiveWordFilter implements ISensitiveWordFilter {

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

    public List<SensitiveWordContext> query(String str) {
        List<SensitiveWordContext> sensitiveList = new ArrayList<>();
        TrieNode currentNode = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (currentNode == null) {
                currentNode = root;
            }
            currentNode = currentNode.getChildNode(ch);
            for (TrieNode node = currentNode; node != root && node != null; node = node.getFailNode()) {
                if (node.isEndChar()) {
                    sensitiveList.add(new SensitiveWordContext(str.substring(i - node.getLength() + 1, i + 1), i - node.getLength() + 1, i));
                }
            }
            if (currentNode != null && currentNode.isEndChar()) {
                currentNode = currentNode.getFailNode();
            }
        }
        return sensitiveList;
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

    @Override
    public void initSensitive() {
        readText("/Users/emcikem/IdeaProjects/keyword/sensitive-word/src/main/resources/sensitive.txt", this);
        this.getFail();
    }

    @Override
    public boolean contains(String target) {
        return query(target).isEmpty();
    }

    @Override
    public List<String> findAll(String target) {
        return query(target).stream()
                .map(SensitiveWordContext::getSensitiveWord)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensitiveWordContext> findAllContext(String target) {
        return query(target);
    }

    @Override
    public String replace(String target) {
        return this.replace(target, CharConst.STAR);
    }

    @Override
    public String replace(String target, char ch) {
        List<SensitiveWordContext> result = query(target);
        char[] chars = target.toCharArray();
        result.forEach(context -> {
            for (int i = context.getStartIndex(); i <= context.getEndIndex(); i++) {
                chars[i] = ch;
            }
        });
        return Arrays.toString(chars);
    }

    @Override
    public String replace(String target, ISensitiveWordReplace replace) {
        int var1 = 0, var2 = 0;
        List<SensitiveWordContext> result = query(target);
        StringBuilder stringBuilder = new StringBuilder();
        while (var2 < result.size()) {
            SensitiveWordContext context = result.get(var2);
            while (var1 < context.getStartIndex()) {
                stringBuilder.append(target.charAt(var1++));
            }
            stringBuilder.append(replace.replace(context.getSensitiveWord()));
            var2 = context.getEndIndex() + 1;
        }
        return stringBuilder.toString();
    }
}
