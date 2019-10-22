package com.zcy.server.program.common.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-10-16 16:20
 */
@Component
public class FileService {

    public String getFileContent(String fileUrl) throws Exception {
        // 要读取以上路径的input。txt文件
        File file = new File(fileUrl);
        // 建立一个输入流对象reader
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file));
        // 建立一个对象，它把文件内容转成计算机能读懂的语言
        BufferedReader br = new BufferedReader(reader);
        String content = "";
        String line = br.readLine();
        while (line != null) {
            content = content + line;
            // 一次读入一行数据
            line = br.readLine();
        }

        return content;
    }

}
