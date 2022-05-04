package com.lyq.sensitiveword;

import com.lyq.sensitiveword.model.WordContext;
import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import com.lyq.sensitiveword.service.helper.SensitiveHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;

/**
 * @author Emcikem
 * @create 2022/5/2
 * @desc 自定义替换策略
 */
public class MyReplaceServiceTest extends BaseTest{

    @Autowired
    private MyReplaceService myReplaceService;

    @Autowired
    private ISensitiveWordFilter iSensitiveWordFilter;

    @Autowired
    private SensitiveHelper sensitiveHelper;

    @Test
    public void test() throws FileNotFoundException {
        iSensitiveWordFilter.initSensitive();
        String str = iSensitiveWordFilter.replace("陈强就个猪", myReplaceService);
        System.out.println(str);
    }

    @Test
    public void test1() {
        sensitiveHelper.replace("陈强就是个猪", WordContext.defaultWordContext());
    }
}
