package com.lyq.sensitiveword.service.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc 获取敏感词，同时移除白名单
 */
@Service
public class SensitiveWordData {

    @Value("#{'${sensitive.filePath}'.split(',')}")
    private List<String> sensitivePath;

    public List<String> getData() throws FileNotFoundException {
        Set<String> sensitive = new HashSet<>();
        for (String filePath : sensitivePath) {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
            if (resourceAsStream == null) {
                throw new FileNotFoundException();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
                String lineTxt;
                // 逐行读取
                while ((lineTxt = br.readLine()) != null) {
                    // 输出内容到控制台
                    lineTxt = lineTxt.replace(" ", "");
                    if (sensitive.contains(lineTxt)) {
                        continue;
                    }
                    sensitive.add(lineTxt);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList(sensitive);
    }


}
