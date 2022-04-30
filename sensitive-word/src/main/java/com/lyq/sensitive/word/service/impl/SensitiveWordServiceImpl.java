package com.lyq.sensitive.word.service.impl;

import com.lyq.sensitive.word.constant.CharConst;
import com.lyq.sensitive.word.service.ISensitiveWordReplace;
import com.lyq.sensitive.word.service.ISensitiveWordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
@Service
public class SensitiveWordServiceImpl implements ISensitiveWordService {


    @Override
    public boolean contains(String target) {
        return false;
    }

    @Override
    public List<String> findAll(String target) {
        return null;
    }

    @Override
    public String replace(String target, ISensitiveWordReplace replace) {
        return null;
    }

    @Override
    public String replace(String target, char replaceChar) {
        return null;
    }

    @Override
    public String replace(String target) {
        return this.replace(target, CharConst.STAR);
    }
}
