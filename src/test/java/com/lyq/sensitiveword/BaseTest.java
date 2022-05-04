package com.lyq.sensitiveword;

import com.lyq.sensitiveword.service.ac.SensitiveWordFilter;
import com.lyq.sensitiveword.service.helper.SensitiveHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
public class BaseTest {

    @Autowired
    private SensitiveWordFilter iSensitiveWordFilter;


    @Autowired
    private SensitiveHelper sensitiveHelper;

    @Test
    void contextLoads() {
    }


    @Test
    public void test() throws FileNotFoundException {
        iSensitiveWordFilter.initSensitive();
        List<String> aaa = iSensitiveWordFilter.findAll("陈强是一个情色大师");
        System.out.println(aaa);
    }

    @Test
    public void tes1() {

        
    }
}
