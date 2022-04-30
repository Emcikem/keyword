package com.lyq.sensitive.word.service;

import java.util.List;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 拒绝出现的数据-返回的内容被当做是敏感词
 */
public interface IWordDeny {

    /**
     * 获取结果
     */
    List<String> deny();
}
