package com.lyq.sensitiveword.service.helper;


import com.lyq.sensitiveword.model.SensitiveWordContext;
import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import com.lyq.sensitiveword.service.ISensitiveWordReplace;
import com.lyq.sensitiveword.service.data.SensitiveWordData;
import com.lyq.sensitiveword.service.impl.CharFormatServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 整合操作
 * 1. 格式化
 * 2. 检测敏感词
 * 3. 替换
 * @author Emcikem
 * @create 2022/5/2
 */
@Service
public class SensitiveHelper {

    private boolean hasInitSensitive;

    @Resource
    private CharFormatServiceImpl charFormatService;

    @Resource
    private ISensitiveWordFilter iSensitiveWordFilter;

    @Resource
    private SensitiveWordData sensitiveWordData;


    private String format(String target, WordContext context) {
        char[] chars = target.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = charFormatService.format(chars[i], context);
        }

        return String.valueOf(chars);
    }


    /**
     * 单例模式初始化敏感词
     */
    private void checkState() {
        synchronized (this) {
            if (!this.hasInitSensitive) {
                try {
                    List<String> data = sensitiveWordData.getData();
                    data.forEach(word -> iSensitiveWordFilter.addSensitive(word));
                    iSensitiveWordFilter.flush();
                    this.hasInitSensitive = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 是否包含敏感词
     */
    public boolean contains(String target, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.contains(filterStr);
    }

    /**
     * 按照顺序返回所有的敏感词
     */
    public List<String> findAll(String target, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.findAll(filterStr);
    }

    /**
     * 返回具体消息
     */
    List<SensitiveWordContext> findAllContext(String target, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.findAllContext(filterStr);
    }

    /**
     * 默认的策略
     */
    public String replace(String target) {
        return replace(target, WordContext.defaultWordContext());
    }

    /**
     * 默认用*替换
     */
    public String replace(String target, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.replace(target, filterStr);
    }

    /**
     * 自定义替换词
     */
    public String replace(String target, char ch, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.replace(target, ch, filterStr);
    }

    /**
     * 自定义替换策略
     */
    public String replace(String target, ISensitiveWordReplace replace, WordContext context) {
        checkState();
        String filterStr = format(target, context);
        return iSensitiveWordFilter.replace(target, replace, filterStr);
    }

    /**
     * 更改敏感词后的变动
     */
    public void reFlush() {
        this.hasInitSensitive = false;
        checkState();
    }
}
