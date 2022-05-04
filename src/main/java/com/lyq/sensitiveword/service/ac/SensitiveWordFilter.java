package com.lyq.sensitiveword.service.ac;

import cn.hutool.core.util.StrUtil;
import com.lyq.sensitiveword.constant.CharConst;
import com.lyq.sensitiveword.model.SensitiveWordContext;
import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import com.lyq.sensitiveword.service.ISensitiveWordReplace;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc ac自动机
 */
@Service
public class SensitiveWordFilter implements ISensitiveWordFilter {

    /**
     * 根节点
     */
    private final TrieNode root = new TrieNode(true);


    private void insert(String str) {
        if (StrUtil.isEmpty(str)) {
            return;
        }
        TrieNode currentState = this.root;
        for (Character character : str.toCharArray()) {
            currentState = currentState.insert(character);
        }
        currentState.setLength(str.length());
    }

    private void getFail() {
        Queue<TrieNode> queue = new LinkedList<>();
        // 1. 将深度为1的节点的fail指针指向根节点
        // 特殊处理：第二层要特殊处理，将这层中的节点的失败路径直接指向父节点(也就是根节点)
        for (TrieNode depthOneState : this.root.children()) {
            depthOneState.setFail(this.root);
            queue.add(depthOneState);
        }

        // 2. 进行dfs，求出所有点的fail指针，策略，我的fail指针就是我父亲节点的fail指针下得与我相同属性的节点
        while (!queue.isEmpty()) {
            TrieNode parentNode = queue.poll();
            for (Character ch : parentNode.getChildren().keySet()) {
                TrieNode childNode = parentNode.find(ch);
                queue.add(childNode);
                TrieNode failNode = parentNode.getFail().nextState(ch);
                childNode.setFail(failNode);
                // 这里，如果我符合的话，那么我的fail指针的nextState节点也符合，我们的后缀相同
            }
        }
    }

    private List<SensitiveWordContext> query(String str) {
        List<SensitiveWordContext> contextList = new ArrayList<>();
        TrieNode currentState = this.root;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            currentState = currentState.nextState(ch);
            if (currentState.getLength() != 0) {
                contextList.add(new SensitiveWordContext(str.substring(i - currentState.getLength() + 1, i + 1), i - currentState.getLength() + 1, i));
            }
            // TODO:策略是什么，下面为什么要进行这一步？优先过滤长的还是优先过滤短的？
//            for (TrieNode node = currentState; node != null && node != this.root; node = node.getFail()) {
//                if (node.isEnd()) { // 这么读取会有重复
//                    contextList.add(new SensitiveWordContext(str.substring(i - node.getLength() + 1, i + 1), i - node.getLength() + 1, i));
//                }
//            }
        }
        return contextList;
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
    public String replace(String target, String filterStr) {
        return this.replace(target, CharConst.STAR, filterStr);
    }

    @Override
    public String replace(String target, char ch, String filterStr) {
        List<SensitiveWordContext> result = query(filterStr);
        char[] chars = target.toCharArray();
        result.forEach(context -> {
            for (int i = context.getStartIndex(); i <= context.getEndIndex(); i++) {
                chars[i] = ch;
            }
        });
        return String.valueOf(chars);
    }

    @Override
    public String replace(String target, ISensitiveWordReplace replace, String filterStr) {
        int var1 = 0, var2 = 0;
        List<SensitiveWordContext> result = query(filterStr);
        StringBuilder stringBuilder = new StringBuilder();
        while (var2 < result.size()) {
            SensitiveWordContext context = result.get(var2);
            while (var1 < context.getStartIndex()) {
                stringBuilder.append(target.charAt(var1++));
            }
            stringBuilder.append(replace.replace(context.getSensitiveWord()));
            var1 = context.getEndIndex() + 1;
            var2++;
        }
        while (var1 < target.length()) {
            stringBuilder.append(target.charAt(var1++));
        }
        return stringBuilder.toString();
    }

    public void addSensitive(String sensitiveWord) {
        this.insert(sensitiveWord);
    }

    public void flush(){
        this.getFail();
    }
}
