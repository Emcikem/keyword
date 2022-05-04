package com.lyq.sensitiveword;

import com.lyq.sensitiveword.service.ISensitiveWordReplace;
import org.springframework.stereotype.Service;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc
 */
@Service
public class MyReplaceService implements ISensitiveWordReplace {

    @Override
    public String replace(String str) {
        if (str.equals("陈强")) {
            return "傻蛋陈强";
        }
        return str;
    }
}
