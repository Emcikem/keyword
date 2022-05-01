package com.lyq.sensitive.word.service.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 获取敏感词，同时移除白名单
 */
@Service
public class SensitiveWordData {

    public List<String> getWordData() {
        return new ArrayList<>();
    }
}
