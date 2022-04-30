package com.lyq.sensitive.word.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lyq.sensitive.word.WordContext;
import com.lyq.sensitive.word.service.IWordMap;
import org.springframework.stereotype.Service;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public class SensitiveWordMap implements IWordMap {


    @Override
    public boolean contains(String target, WordContext context) {
        if (StrUtil.isEmpty(target)) {
            return false;
        }

        for (int i = 0; i < target.length(); i++) {

        }
        return false;
    }
}
