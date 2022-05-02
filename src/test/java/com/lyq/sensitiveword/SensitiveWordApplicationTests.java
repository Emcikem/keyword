package com.lyq.sensitiveword;

import com.lyq.sensitiveword.service.ac.SensitiveWordFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class SensitiveWordApplicationTests {

    @Autowired
    private SensitiveWordFilter iSensitiveWordFilter;

    @Test
    void contextLoads() {
    }


    @Test
    public void test() throws FileNotFoundException {
        iSensitiveWordFilter.initSensitive();
        List<String> aaa = iSensitiveWordFilter.findAll("陈强是一个情色大师");
        System.out.println(aaa);
    }
}
