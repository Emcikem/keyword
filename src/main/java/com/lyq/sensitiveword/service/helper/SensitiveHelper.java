package com.lyq.sensitiveword.service.helper;


import com.lyq.sensitiveword.model.SensitiveWordContext;
import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import com.lyq.sensitiveword.service.ISensitiveWordReplace;
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
    private CharFormatServiceImpl charFormat;

    @Resource
    private ISensitiveWordFilter iSensitiveWordFilter;


    private String format(String target, WordContext context) {
        char[] chars = target.toCharArray();
        for (char ch : chars) {
            ch = charFormat.format(ch, context);
        }
        return String.valueOf(chars);
    }


    private void checkState() {
        synchronized (this) {
            if (!this.hasInitSensitive) {
                try {
                    iSensitiveWordFilter.initSensitive();
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
        target = format(target, context);
        return iSensitiveWordFilter.contains(target);
    }

    /**
     * 按照顺序返回所有的敏感词
     */
    public List<String> findAll(String target, WordContext context) {
        checkState();
        target = format(target, context);
        return iSensitiveWordFilter.findAll(target);
    }

    /**
     * 返回具体消息
     */
    List<SensitiveWordContext> findAllContext(String target, WordContext context) {
        checkState();
        target = format(target, context);
        return iSensitiveWordFilter.findAllContext(target);
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
        target = format(target, context);
        return iSensitiveWordFilter.replace(target);
    }

    /**
     * 自定义替换词
     */
    public String replace(String target, char ch, WordContext context) {
        checkState();
        target = format(target, context);
        return iSensitiveWordFilter.replace(target, ch);
    }

    /**
     * 自定义替换策略
     */
    public String replace(String target, ISensitiveWordReplace replace, WordContext context) {
        checkState();
        target = format(target, context);
        return iSensitiveWordFilter.replace(target, replace);
    }

    /**
     * 更改敏感词后的变动
     */
    public void reFlush() {
        this.hasInitSensitive = false;
        checkState();
    }
}
