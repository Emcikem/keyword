package com.lyq.sensitiveword;

import com.lyq.sensitiveword.service.ISensitiveWordFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SensitiveWordApplicationTests {

    @Autowired
    private ISensitiveWordFilter iSensitiveWordFilter;

    @Test
    void contextLoads() {
    }


    @Test
    public void test() {
        iSensitiveWordFilter.initSensitive();
        String str = iSensitiveWordFilter.replace("陈强是傻逼吧");
        System.out.println(str);
    }
}
