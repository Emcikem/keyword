package com.lyq.sensitiveword;

import com.lyq.sensitiveword.service.helper.SensitiveHelper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Emcikem
 * @create 2022/5/4
 * @desc
 */
public class SensitiveFilerTest extends BaseTest {

    @Autowired
    private SensitiveHelper sensitiveHelper;


    @Before
    public void initWord() {
        sensitiveHelper.reFlush();
    }

    @Test
    public void baseFiler() {
        long start = System.currentTimeMillis();
        String str = sensitiveHelper.replace("敏感词1和敏感词2都是不好的敏感词");
        long end = System.currentTimeMillis();
        System.out.println(str);
        System.out.println(end - start);
    }

    @Test
    public void ignoreChineseStyle() {
        long start = System.currentTimeMillis();
        String str = sensitiveHelper.replace("把敏感詞3過濾一下啊");
        long end = System.currentTimeMillis();
        System.out.println(str);
        System.out.println(end - start);
    }


    @Test
    public void ignoreEnglishStyle() {
        long start = System.currentTimeMillis();
        String str = sensitiveHelper.replace("FⓊc⒦");
        long end = System.currentTimeMillis();
        System.out.println(str);
        System.out.println(end - start);
    }

    @Test
    public void ignoreWidth(){

    }

    @Test
    public void myReplaceTest() {
        long start = System.currentTimeMillis();
        String str = sensitiveHelper.replace("陈强是一个敏感词1，所有要过滤掉");
        long end = System.currentTimeMillis();
        System.out.println(str);
        System.out.println(end - start);
    }
}
